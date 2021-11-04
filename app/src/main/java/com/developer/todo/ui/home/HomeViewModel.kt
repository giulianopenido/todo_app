package com.developer.todo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developer.todo.model.Category
import com.developer.todo.repository.CategoryRepository

class HomeViewModel : ViewModel() {

    var categories : MutableLiveData<MutableList<Category>>
    private var repository : CategoryRepository

    init {
        repository = CategoryRepository()
        categories = repository.getAll()
    }

}