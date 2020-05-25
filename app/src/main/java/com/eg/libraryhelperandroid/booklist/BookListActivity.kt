package com.eg.libraryhelperandroid.booklist

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eg.libraryhelperandroid.R
import com.eg.libraryhelperandroid.util.ToastUtil
import kotlinx.android.synthetic.main.activity_book_list.*


class BookListActivity : AppCompatActivity() {
    private val mainHandler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        val q = intent.getStringExtra("q") as String

        //RecyclerView
        rv_bookList.layoutManager = LinearLayoutManager(this@BookListActivity)
        val bookListAdapter = BookListAdapter(this@BookListActivity, mainHandler, q)
        rv_bookList.adapter = bookListAdapter
        //分割线
        rv_bookList.addItemDecoration(
            DividerItemDecoration(this@BookListActivity, DividerItemDecoration.VERTICAL)
        )
        //RecyclerView监听
        rv_bookList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0) return
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                //现在最后显示的是第几个
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                //总共已经加载了几个
                val totalSize = bookListAdapter.data.size
                //还剩几个没显示
                val diff = totalSize - 1 - lastVisibleItemPosition
                //继续加载
                if (diff <= 5 && bookListAdapter.isLoading == false
                    && bookListAdapter.isFinished == false
                ) {
                    bookListAdapter.loadData()
                }
                //触底提示
                val lastCompletelyVisibleItemPosition =
                    layoutManager.findLastCompletelyVisibleItemPosition()
                if (bookListAdapter.isFinished
                    && lastCompletelyVisibleItemPosition == layoutManager.itemCount - 1
                ) {
                    ToastUtil.show(
                        this@BookListActivity,
                        "The end! total: " + layoutManager.itemCount
                    );
                }
            }
        })

    }
}
