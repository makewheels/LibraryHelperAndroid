package com.eg.libraryhelperandroid.booklist;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eg.libraryhelperandroid.R;
import com.eg.libraryhelperandroid.bookdetail.BookDetailActivity;
import com.eg.libraryhelperandroid.util.OkHttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {
    private Context context;
    private Handler handler;
    private String q;
    private List<BookQueryRecord> data;
    private int page = 0;
    private int size = 20;

    private boolean isFinished = false;
    private boolean isLoading = false;

    public List<BookQueryRecord> getData() {
        return data;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public BookListAdapter(Context context, Handler handler, String q) {
        this.context = context;
        this.handler = handler;
        this.q = q;
        data = new ArrayList<>();
        //初始化数据
        loadData();

    }

    /**
     * 加载数据
     */
    public void loadData() {
        isLoading = true;
        page++;
        //发请求
        String url = "/book/search?q=" + q + "&page=" + page + "&size=" + size;
        Call call = OkHttpUtil.getCall(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                isLoading = false;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                BookQueryResponse bookQueryResponse = new Gson().fromJson(json, BookQueryResponse.class);
                int amount = bookQueryResponse.getAmount();
                if (amount == 0) {
                    isFinished = true;
                }
                data.addAll(bookQueryResponse.getBookRecordList());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                        isLoading = false;
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookQueryRecord book = data.get(position);
        holder.tv_title.setText(book.getTitle());
        Glide.with(context).load(book.getCoverUrl()).into(holder.iv_cover);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView iv_cover;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            iv_cover = itemView.findViewById(R.id.iv_cover);
            //点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    BookQueryRecord book = data.get(position);
                    String mangoId = book.getMangoId();
                    //启动详情页Activity
                    Intent intent = new Intent(context, BookDetailActivity.class);
                    intent.putExtra("mangoId", mangoId);
                    context.startActivity(intent);
                }
            });
        }

    }
}
