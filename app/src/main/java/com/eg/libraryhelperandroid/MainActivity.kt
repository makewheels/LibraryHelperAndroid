package com.eg.libraryhelperandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.eg.libraryhelperandroid.booklist.BookListActivity
import com.eg.libraryhelperandroid.booklist.BookQueryRecord
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setClickListener()

    }

    private fun setClickListener() {
        //搜索按钮点击监听
        btn_search.setOnClickListener {
            //搜索内容
            var q = et_search.text.toString()
            //打开结果列表页
            var intent = Intent(this, BookListActivity::class.java)
            intent.putExtra("q", q)
            startActivity(intent)
        }
    }
}
