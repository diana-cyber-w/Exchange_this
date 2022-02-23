package com.example.exchangethis.presentation.recycler

import com.example.exchangethis.domain.models.Book

interface OnBookClickListener {
    fun onIconClickListener(position: Int)
    fun onItemClickListener(book: Book)
}