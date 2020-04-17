package com.eg.libraryhelperandroid.booklist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eg.libraryhelperandroid.BookService
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

        var q = intent.getStringExtra("q")

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.99.193")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val bookService = retrofit.create(BookService::class.java)
        bookService.queryBookList(q = q as String)
            .enqueue(object : Callback<BookQueryResponse> {
                override fun onResponse(
                    call: Call<BookQueryResponse>,
                    response: Response<BookQueryResponse>
                ) {
                    val bookQueryResponse = response.body() as BookQueryResponse
                    rv_bookList.layoutManager = LinearLayoutManager(this@BookListActivity)
                    rv_bookList.adapter = BookAdapter(bookQueryResponse, this@BookListActivity)
                    //添加Android自带的分割线
                    rv_bookList.addItemDecoration(
                        DividerItemDecoration(this@BookListActivity, DividerItemDecoration.VERTICAL)
                    )
                }

                override fun onFailure(call: Call<BookQueryResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}
