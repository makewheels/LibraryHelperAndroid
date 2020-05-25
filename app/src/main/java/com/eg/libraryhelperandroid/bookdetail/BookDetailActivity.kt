package com.eg.libraryhelperandroid.bookdetail

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.eg.libraryhelperandroid.R
import com.eg.libraryhelperandroid.bookdetail.ui.main.SectionsPagerAdapter
import com.eg.libraryhelperandroid.util.OkHttpUtil
import com.google.android.material.tabs.TabLayout
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

        //tab layout
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val tabAdapter = TabAdapter(
            this, supportFragmentManager
            , FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        viewPager.adapter = tabAdapter
        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    class TabAdapter(private val context: Context, fm: FragmentManager, behavior: Int) :
        FragmentPagerAdapter(fm, behavior) {

        override fun getItem(position: Int): Fragment {
            return CatalogFragment()
        }

        override fun getPageTitle(position: Int): CharSequence? {
            if (position == 0)
                return context.getString(R.string.catalog)
            else
                return context.getString(R.string.summary)
        }

        override fun getCount(): Int {
            return 2
        }
    }

    /**
     * 加载书的详情
     */
    private fun loadBookDetail(mangoId: String) {
        //查询书的详情
        val call = OkHttpUtil.getCall("/book/getBookDetail?mangoId=" + mangoId)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                val bookDetailResponse = Gson().fromJson(json, BookDetailResponse::class.java)
                runOnUiThread(Runnable {
                    tv_title.text = bookDetailResponse.title
                    Glide.with(this@BookDetailActivity)
                        .load(bookDetailResponse.coverUrl)
                        .into(iv_cover)
                    tv_publisher.text = bookDetailResponse.publisher
                    tv_publishDate.text = bookDetailResponse.publishDate
                    tv_catalog.text = bookDetailResponse.catalog
                    tv_summary.text = bookDetailResponse.summary
                })
            }
        })
    }
}
