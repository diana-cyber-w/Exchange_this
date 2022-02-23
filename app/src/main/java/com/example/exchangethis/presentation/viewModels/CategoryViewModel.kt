package com.example.exchangethis.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exchangethis.presentation.models.Category
import com.example.exchangethis.presentation.models.CategoryList

class CategoryViewModel() : ViewModel() {

    val category: LiveData<List<Category>> get() = _category
    private val _category = MutableLiveData<List<Category>>()

    fun getCategory() {
        _category.value = CategoryList.categoryList
    }
}

