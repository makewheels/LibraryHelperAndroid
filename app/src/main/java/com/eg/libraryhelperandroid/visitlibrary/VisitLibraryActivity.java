package com.eg.libraryhelperandroid.visitlibrary;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.eg.libraryhelperandroid.R;
import com.eg.libraryhelperandroid.bookdetail.bean.Position;
import com.eg.libraryhelperandroid.util.OkHttpUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class VisitLibraryActivity extends AppCompatActivity {
    private TextView tv_position;
    private Button btn_up;
    private Button btn_left;
    private Button btn_right;
    private Button btn_down;

    private Position position;
    private List<BookBasicInfo> bookBasicInfoList;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_library);

        tv_position = findViewById(R.id.tv_position);
        btn_up = findViewById(R.id.btn_up);
        btn_left = findViewById(R.id.btn_left);
        btn_right = findViewById(R.id.btn_right);
        btn_down = findViewById(R.id.btn_down);
        recyclerView = findViewById(R.id.recyclerView);

        addListeners();

        position = (Position) getIntent().getSerializableExtra("position");
        tv_position.setText(position.getDetailPosition());
        loadSpecificCellBooks();

    }

    private void addListeners() {
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(VisitLibraryActivity.this, JSON.toJSONString(position)).show();
            }
        });
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 加载指定cell的books
     */
    private void loadSpecificCellBooks() {
        Call call = OkHttpUtil.getCall("/book/getBookIdsByTargetCell?room="
                + position.getRoom() + "&row=" + position.getRow() + "&side=" + position.getSide()
                + "&shelf=" + position.getShelf() + "&level=" + position.getLevel());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                List<String> bookIds = JSON.parseArray(response.body().string(), String.class);
                bookBasicInfoList = new ArrayList<>();
                for (String bookId : bookIds) {
                    BookBasicInfo bookBasicInfo = new BookBasicInfo();
                    bookBasicInfo.setBookId(bookId);
                    bookBasicInfoList.add(bookBasicInfo);
                }
                //获取书的基本信息
                getBookBasicInfoByBookIds();
            }
        });
    }

    /**
     * 获取书的基本信息
     */
    private void getBookBasicInfoByBookIds() {
        //放入bookId
        List<String> bookIdList = new ArrayList<>();
        for (BookBasicInfo bookBasicInfo : bookBasicInfoList) {
            bookIdList.add(bookBasicInfo.getBookId());
        }
        //请求服务器，获取书的基本信息，存入集合
        Call call = null;
        try {
            String bookIdListJson = JSON.toJSONString(bookIdList);
            call = OkHttpUtil.getCall("/book/getBookBasicInfoByBookIds?bookIds="
                    + URLEncoder.encode(bookIdListJson, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                bookBasicInfoList = JSON.parseArray(response.body().string(), BookBasicInfo.class);
                loadToPage();
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }
        });
    }

    /**
     * 加载到页面上
     */
    private void loadToPage() {
        Log.e("tag", JSON.toJSONString(bookBasicInfoList));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(new VisitLibraryAdapter(VisitLibraryActivity.this, bookBasicInfoList));
                recyclerView.setLayoutManager(new GridLayoutManager(VisitLibraryActivity.this, 2));
            }
        });


    }
}