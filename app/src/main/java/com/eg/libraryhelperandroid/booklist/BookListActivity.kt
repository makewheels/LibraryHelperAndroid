package com.eg.libraryhelperandroid.booklist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eg.libraryhelperandroid.BookService
import com.eg.libraryhelperandroid.util.RetrofitUtil
import kotlinx.android.synthetic.main.activity_book_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BookListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.eg.libraryhelperandroid.R.layout.activity_book_list)

        val q = intent.getStringExtra("q") as String

        //发请求，查询bookList
        val bookService = RetrofitUtil.getBookService()
        bookService.queryBookList(q)
            .enqueue(object : Callback<BookQueryResponse> {
                override fun onFailure(call: Call<BookQueryResponse>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<BookQueryResponse>,
                    response: Response<BookQueryResponse>
                ) {
                    //处理响应
                    val bookQueryResponse = response.body() as BookQueryResponse
                    rv_bookList.layoutManager = LinearLayoutManager(this@BookListActivity)
                    rv_bookList.adapter = BookAdapter(bookQueryResponse, this@BookListActivity)
                    //分割线
                    rv_bookList.addItemDecoration(
                        DividerItemDecoration(this@BookListActivity, DividerItemDecoration.VERTICAL)
                    )
                }
            })
    }
}
