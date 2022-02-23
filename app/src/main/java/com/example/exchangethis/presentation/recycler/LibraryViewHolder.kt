package com.example.exchangethis.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.BookItemLayoutBinding
import com.example.exchangethis.domain.models.Book

class LibraryViewHolder(
    itemView: View,
    private val itemClickListener: OnBookClickListener
) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun fromParent(parent: ViewGroup, itemClickListener: OnBookClickListener) =
            LibraryViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.book_item_layout, parent, false),
                itemClickListener
            )
    }

    private val binding: BookItemLayoutBinding by viewBinding(BookItemLayoutBinding::bind)

    private val bookName: TextView by lazy { binding.bookName }
    private val rating: RatingBar by lazy { binding.bookRating }
    private val itemContainer by lazy { binding.book }
    private val favouriteButton by lazy { binding.addToFavourite }

    fun bindView(item: Book) {
        bookName.text = item.bookName
        rating.rating = item.rating.toFloat()
        favouriteButton.isChecked = item.favourite

        itemContainer.setOnClickListener {
            itemClickListener.onItemClickListener(item)
        }

        favouriteButton.setOnClickListener {
            itemClickListener.onIconClickListener(bindingAdapterPosition)
        }
    }
}