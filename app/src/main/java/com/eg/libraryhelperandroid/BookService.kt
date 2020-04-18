package com.eg.libraryhelperandroid

import com.eg.libraryhelperandroid.bookdetail.BookDetailResponse
import com.eg.libraryhelperandroid.booklist.BookQueryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    //查询书列表
    @GET("book/search")
    fun queryBookList(@Query("q") q: String): Call<BookQueryResponse>

    //根据索书号查询书详情
    @GET("book/getBookDetail")
    fun getBookDetailById(@Query("id") id: String): Call<BookDetailResponse>
}