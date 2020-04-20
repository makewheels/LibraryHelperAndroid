package com.eg.libraryhelperandroid.bookdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.eg.libraryhelperandroid.R
import com.eg.libraryhelperandroid.util.OkHttpUtil
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_book_detail.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * 书的详情页
 */
class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        //获取id
        val mangoId = intent.getStringExtra("mangoId") as String

        //加载书的详情
        loadBookDetail(mangoId)

        //监听下拉刷新
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            loadBookDetail(mangoId)
        })

    }

    /**
     * 加载书的详情
     */
    private fun loadBookDetail(mangoId: String) {
        //查询书的详情
        val call = OkHttpUtil.getCall("/book/getBookDetail?mangoId=" + mangoId)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread(Runnable {
                    //取消下拉刷新转动图标
                    swipeRefreshLayout.isRefreshing = false
                })
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                val bookDetailResponse
                        = Gson().fromJson(json, BookDetailResponse::class.java)
                runOnUiThread(Runnable {
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
                })
            }
        })
    }
}
