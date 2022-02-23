package com.example.exchangethis.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.CategoryItemLayoutBinding
import com.example.exchangethis.presentation.models.Category

class CategoryViewHolder(
    itemView: View,
    private val itemClickListener: OnCategoryClickListener
) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun fromParent(parent: ViewGroup, itemClickListener: OnCategoryClickListener) =
            CategoryViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.category_item_layout, parent, false),
                itemClickListener
            )
    }

    private val binding: CategoryItemLayoutBinding by viewBinding(CategoryItemLayoutBinding::bind)

    private val category: TextView by lazy { binding.bookCategory }
    private val categoryContainer by lazy { binding.categoryContainer }

    fun bindView(item: Category) {
        category.text = item.category

        categoryContainer.setOnClickListener {
            itemClickListener.onItemClickListener(item)
        }
    }
}