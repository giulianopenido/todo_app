package com.developer.todo.repository

import androidx.lifecycle.MutableLiveData
import com.developer.todo.base.IRepository
import com.developer.todo.model.Category

class CategoryRepository(): IRepository<Category> {

    override fun getAll(): MutableLiveData<MutableList<Category>> {
        return MutableLiveData(
            mutableListOf(
                Category("Business", 8, 2, "#ED27FF"),
                Category("Personal", 10, 12, "#076AFF"),
                Category("Work", 5, 4, "#0E1F54"),
            )
        )
    }

    override fun create() {

    }

}