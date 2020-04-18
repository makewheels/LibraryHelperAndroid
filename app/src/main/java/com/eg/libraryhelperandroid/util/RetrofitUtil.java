package com.eg.libraryhelperandroid.util;

import com.eg.libraryhelperandroid.BookService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static String BASE_URL = "http://192.168.99.193/libraryapp/";
    private static Retrofit retrofit;
    private static BookService bookService;

    static {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bookService = retrofit.create(BookService.class);
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static BookService getBookService() {
        return bookService;
    }
}
