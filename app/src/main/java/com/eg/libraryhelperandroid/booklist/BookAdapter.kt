package com.eg.libraryhelperandroid.booklist

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eg.libraryhelperandroid.R

class BookAdapter(val bookQueryResponse: BookQueryResponse) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_title: TextView = view.findViewById(R.id.tv_title)
        val tv_publisher: TextView = view.findViewById(R.id.tv_publisher)
        val image_book: ImageView = view.findViewById(R.id.image_book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_list_item, parent, false)
        return ViewHolder(view)
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
    }


}