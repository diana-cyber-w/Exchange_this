package com.example.exchangethis.presentation.recycler

import com.example.exchangethis.presentation.models.Category

interface OnCategoryClickListener {
    fun onItemClickListener(category: Category)
}