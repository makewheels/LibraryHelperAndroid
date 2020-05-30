package com.eg.libraryhelperandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.eg.libraryhelperandroid.booklist.BookListActivity
import com.eg.libraryhelperandroid.booklist.BookQueryRecord
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //检查更新
        checkUpdate()
        setClickListener()

    }

    /**
     * 检查更新
     */
    private fun checkUpdate() {
        val packageInfo     = packageManager.getPackageInfo(packageName, 0)
        val versionCode = packageInfo.versionCode
        return
    }

    private fun setClickListener() {
        //搜索按钮点击监听
        btn_search.setOnClickListener {
            //搜索内容
            val q = et_search.text.toString()
            //打开结果列表页
            val intent = Intent(this, BookListActivity::class.java)
            intent.putExtra("q", q)
            startActivity(intent)
        }
    }
}
