package com.example.exchangethis.presentation.recycler

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangethis.presentation.models.Category

class CategoryAdapter(
    private val itemClickListener: OnCategoryClickListener
) : RecyclerView.Adapter<CategoryViewHolder>() {

    private var items: List<Category> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.fromParent(parent, itemClickListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(data: List<Category>) {
        items = data
        notifyDataSetChanged()
    }
}