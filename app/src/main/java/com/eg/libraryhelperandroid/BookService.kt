package com.eg.libraryhelperandroid

import com.eg.libraryhelperandroid.booklist.BookQueryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("/libraryapp/book/search")
    fun queryBookList(@Query("q") q: String): Call<BookQueryResponse>
}