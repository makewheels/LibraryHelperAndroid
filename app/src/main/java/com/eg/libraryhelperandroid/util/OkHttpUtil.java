package com.eg.libraryhelperandroid.util;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpUtil {
    public static String BASE_URL = "http://192.168.99.193/libraryapp";

    public static Call getCall(String url) {
        Request request = new Request.Builder()
                .url(BASE_URL + url)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        return okHttpClient.newCall(request);
    }

}
