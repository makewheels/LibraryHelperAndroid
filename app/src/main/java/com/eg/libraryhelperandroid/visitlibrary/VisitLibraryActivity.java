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
import com.eg.libraryhelperandroid.bookdetail.bean.PositionResponse;
import com.eg.libraryhelperandroid.util.OkHttpUtil;
import com.eg.libraryhelperandroid.visitlibrary.bean.visitlibrary.CellInfo;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class VisitLibraryActivity extends AppCompatActivity {
    private TextView tv_position;
    private Button btn_up;
    private Button btn_left;
    private Button btn_right;
    private Button btn_down;

    //当前cell信息
    private CellInfo cellInfo = new CellInfo();
    //书的信息列表
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

        init();
        loadData();
        addListeners();
    }

    private void addListeners() {
        //上下左右按钮
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cellInfo.setCurrent(cellInfo.getUp());
                loadData();
            }
        });
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cellInfo.setCurrent(cellInfo.getLeft());
                loadData();
            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cellInfo.setCurrent(cellInfo.getRight());
                loadData();
            }
        });
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cellInfo.setCurrent(cellInfo.getDown());
                loadData();
            }
        });
    }

    /**
     * 初始化页面
     */
    private void init() {
        PositionResponse current = (PositionResponse) getIntent().getSerializableExtra("position");
        cellInfo.setCurrent(current);
    }

    /**
     * 加载数据
     */
    private void loadData() {
        //加载cell数据
        PositionResponse currentPositionResponse = cellInfo.getCurrent();
        Call call = OkHttpUtil.getCall("/book/getTargetCellInfo?room="
                + currentPositionResponse.getRoom()
                + "&row=" + currentPositionResponse.getRow()
                + "&side=" + currentPositionResponse.getSide()
                + "&shelf=" + currentPositionResponse.getShelf()
                + "&level=" + currentPositionResponse.getLevel());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String string = response.body().string();
                cellInfo = JSON.parseObject(string, CellInfo.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String detailPosition = cellInfo.getCurrent().getDetailPosition();
                        Log.e("tag", detailPosition);
                        tv_position.setText(cellInfo.getCurrent().getDetailPosition());
                    }
                });
                loadBookBasicInfoByBookIds();
            }
        });
    }

    /**
     * 获取书的基本信息
     */
    private void loadBookBasicInfoByBookIds() {
        //请求服务器，获取书的基本信息，存入集合
        Call call = null;
        try {
            String bookIdListJson = JSON.toJSONString(cellInfo.getBookIdList());
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
     * 加载图片和文字到页面上
     */
    private void loadToPage() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(
                        new VisitLibraryAdapter(VisitLibraryActivity.this, bookBasicInfoList));
                recyclerView.setLayoutManager(
                        new GridLayoutManager(VisitLibraryActivity.this, 2));
            }
        });
    }
}