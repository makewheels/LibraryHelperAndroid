package com.eg.libraryhelperandroid.visitlibrary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eg.libraryhelperandroid.R;

import java.util.List;

public class VisitLibraryAdapter extends RecyclerView.Adapter<VisitLibraryAdapter.ViewHolder> {
    private final Context context;
    private final List<BookBasicInfo> bookBasicInfoList;

    public VisitLibraryAdapter(Context context, List<BookBasicInfo> bookBasicInfoList) {
        this.context = context;
        this.bookBasicInfoList = bookBasicInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_visit_library_recyclerview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookBasicInfo bookBasicInfo = bookBasicInfoList.get(position);
        String coverUrl = bookBasicInfo.getCoverUrl();
        String title = bookBasicInfo.getTitle();
        Glide.with(context).load(coverUrl).into(holder.imageView);
        holder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return bookBasicInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
