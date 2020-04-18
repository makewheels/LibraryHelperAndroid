package com.eg.libraryhelperandroid.booklist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eg.libraryhelperandroid.R
import com.eg.libraryhelperandroid.bookdetail.BookDetailActivity

class BookAdapter(
    private val bookQueryResponse: BookQueryResponse,
    private val context: Context
) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_title: TextView = view.findViewById(R.id.tv_title)
        val tv_publisher: TextView = view.findViewById(R.id.tv_publisher)
        val image_book: ImageView = view.findViewById(R.id.iv_cover)
        val book_list_item_parent: LinearLayout = view.findViewById(R.id.book_list_item_parent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_list_item, parent, false)
        val viewHolder = ViewHolder(view)
        //点击事件
        viewHolder.book_list_item_parent.setOnClickListener {
            val position = viewHolder.adapterPosition
            val bookQueryRecord = bookQueryResponse.bookRecordList?.get(position)
            val id = bookQueryRecord?.id
            //打开图书详情页
            val intent = Intent(parent.context, BookDetailActivity::class.java)
            intent.putExtra("id", id)
            parent.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun getItemCount(): Int = bookQueryResponse.amount

    override fun onBindViewHolder(holder: BookAdapter.ViewHolder, position: Int) {
        val book = bookQueryResponse.bookRecordList?.get(position)
        if (book != null) {
            holder.tv_title.text = book.title
        }
        if (book != null) {
            holder.tv_publisher.text = book.publisher
        }
        Glide.with(context).load(book?.coverImageUrl).into(holder.image_book)
    }


}