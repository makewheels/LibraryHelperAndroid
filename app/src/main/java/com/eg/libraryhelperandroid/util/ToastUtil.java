package com.eg.libraryhelperandroid.util;

import android.content.Context;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class ToastUtil {
    public static void show(Context context, String text) {
        Toasty.info(context, text, Toast.LENGTH_SHORT, true).show();
    }
}
