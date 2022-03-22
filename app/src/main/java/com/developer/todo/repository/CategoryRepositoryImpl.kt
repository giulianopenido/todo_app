package com.developer.todo.repository

import androidx.lifecycle.MutableLiveData
import com.developer.todo.base.IRepository
import com.developer.todo.model.Category

class CategoryRepositoryImpl: CategoryRepository {

    override suspend fun getAll(): MutableLiveData<MutableList<Category>> {
        return MutableLiveData(
            mutableListOf(
                Category(1,"Business", 8, 2, "#ED27FF"),
                Category(2, "Personal", 10, 12, "#076AFF"),
                Category(3, "Work", 5, 4, "#0E1F54"),
            )
        )
    }



}