package com.eg.libraryhelperandroid.bookdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.eg.libraryhelperandroid.R
import com.eg.libraryhelperandroid.util.OkHttpUtil
import com.google.android.material.tabs.TabLayoutMediator
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
    private val catalogFragment: CatalogFragment = CatalogFragment()
    private val summaryFragment: SummaryFragment = SummaryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        //tab layout
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 2
            }

            override fun createFragment(position: Int): Fragment {
                if (position == 0)
                    return catalogFragment
                else
                    return summaryFragment
            }
        }
        TabLayoutMediator(tabLayout, viewPager) { tabLayout, position ->
            if (position == 0)
                tabLayout.text = getString(R.string.catalog)
            else
                tabLayout.text = getString(R.string.summary)
        }.attach()

        //获取id
        val mangoId = intent.getStringExtra("mangoId") as String
        //加载书的详情
        loadBookDetail(mangoId)

        //给游图书馆添加按钮监听
        addVisitLibraryButtonListener()

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
                    val author = StringBuilder()
                    bookDetailResponse.authorList?.forEach { each ->
                        author.append(each)
                    }
                    tv_author.text = author
                    tv_publisher.text = bookDetailResponse.publisher
                    tv_publishDate.text = bookDetailResponse.publishDate
                    tv_position.text = bookDetailResponse.position
                    //准备tab页目录和摘要的数据
                    val catalog = bookDetailResponse.catalog.toString()
                    if (catalog != "")
                        TabData.catalog = catalog

                    val summary = bookDetailResponse.catalog.toString()
                    if (summary != "")
                        TabData.summary = bookDetailResponse.summary.toString()
                })
            }
        })
    }

    private fun addVisitLibraryButtonListener() {
        btn_visit.setOnClickListener({

        })
    }
}
