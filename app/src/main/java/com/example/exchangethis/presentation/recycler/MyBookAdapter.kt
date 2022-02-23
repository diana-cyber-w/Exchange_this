package com.example.exchangethis.presentation.recycler

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangethis.domain.models.Book

class MyBookAdapter(
    private val itemClickListener: OnBookClickListener
) : RecyclerView.Adapter<MyBookViewHolder>() {

    private var items: List<Book> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBookViewHolder {
        return MyBookViewHolder.fromParent(parent, itemClickListener)
    }

    override fun onBindViewHolder(holder: MyBookViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(data: List<Book>) {
        items = data
        notifyDataSetChanged()
    }
}