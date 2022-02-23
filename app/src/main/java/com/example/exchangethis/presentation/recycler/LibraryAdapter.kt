package com.example.exchangethis.presentation.recycler

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangethis.domain.models.Book
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl

class LibraryAdapter(
    private val itemClickListener: OnBookClickListener
) : RecyclerView.Adapter<LibraryViewHolder>() {

    private var items: List<Book> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        return LibraryViewHolder.fromParent(parent, itemClickListener)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        val prefs = SharedPreferenceManagerImpl(holder.itemView.context)
        val item = items[position]
        val list = items.toMutableList()
        list[position] = item.copy(favourite = prefs.getBoolean(items[position].bookName))
        holder.bindView(items[position])
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(data: List<Book>) {
        items = data
        notifyDataSetChanged()
    }
}