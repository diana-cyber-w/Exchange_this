package com.example.exchangethis.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.MyBookItemLayoutBinding
import com.example.exchangethis.domain.models.Book

class MyBookViewHolder(
    itemView: View,
    private val itemClickListener: OnBookClickListener
) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun fromParent(parent: ViewGroup, itemClickListener: OnBookClickListener) =
            MyBookViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.my_book_item_layout, parent, false),
                itemClickListener
            )
    }

    private val binding: MyBookItemLayoutBinding by viewBinding(MyBookItemLayoutBinding::bind)

    private val bookName: TextView by lazy { binding.bookName }
    private val rating: RatingBar by lazy { binding.bookRating }
    private val deleteButton: ImageButton by lazy { binding.deleteMyBook }
    private val itemContainer by lazy { binding.myBook }

    fun bindView(item: Book) {
        bookName.text = item.bookName
        rating.rating = item.rating.toFloat()

        deleteButton.setOnClickListener {
            itemClickListener.onIconClickListener(bindingAdapterPosition)
        }

        itemContainer.setOnClickListener {
            itemClickListener.onItemClickListener(item)
        }
    }
}