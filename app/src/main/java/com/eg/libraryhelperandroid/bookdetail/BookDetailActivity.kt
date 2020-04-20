package com.eg.libraryhelperandroid.bookdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.eg.libraryhelperandroid.R
import com.eg.libraryhelperandroid.util.RetrofitUtil
import kotlinx.android.synthetic.main.activity_book_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 书的详情页
 */
class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        //获取id
        val intent = intent
        val id = intent.getStringExtra("id") as String

        //加载书的详情
        loadBookDetail(id)

        //监听下拉刷新
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            loadBookDetail(id)
        })

    }

    /**
     * 加载书的详情
     */
    private fun loadBookDetail(id: String) {
        //查询书的详情
        val bookService = RetrofitUtil.getBookService()
        bookService.getBookDetailById(id).enqueue(object : Callback<BookDetailResponse> {
            override fun onFailure(call: Call<BookDetailResponse>, t: Throwable) {
                //取消下拉刷新转动图标
                swipeRefreshLayout.isRefreshing = false
            }

            override fun onResponse(
                call: Call<BookDetailResponse>,
                response: Response<BookDetailResponse>
            ) {
                val bookDetailResponse = response.body() as BookDetailResponse
                tv_title.text = bookDetailResponse.title
                Glide.with(this@BookDetailActivity)
                    .load(bookDetailResponse.coverImageUrl)
                    .into(iv_cover)
                tv_publisher.text = bookDetailResponse.publisher
                tv_publishDate.text = bookDetailResponse.publishDate
                tv_catalog.text = bookDetailResponse.catalog
                tv_summary.text = bookDetailResponse.summary
                //取消下拉刷新转动图标
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }
}
