package com.developer.todo.repository

import androidx.lifecycle.MutableLiveData
import com.developer.todo.base.IRepository
import com.developer.todo.model.Category
import com.developer.todo.model.Task

class TaskRepositoryImpl(): TaskRepository {

    override fun getAll(): MutableLiveData<MutableList<Task>> {
        val cat1 = Category("Business", 8, 2, "#ED27FF")
        val cat2 = Category("Personal", 10, 12, "#076AFF")
        return MutableLiveData(
            mutableListOf(
                Task("Daily meeting with team", "", cat1),
                Task("Pay for rent", "", cat2, true),
                Task("Check e-mails", "", cat1),
                Task("Lunch with Emma", "", cat1),
                Task("Meditation", "", cat2),
                Task("Teste", "", cat1),
                Task("Pay for rent", "", cat2, true),
                Task("Check e-mails", "", cat1),
                Task("Lunch with Emma", "", cat1),
                Task("Meditation", "", cat2)
            )
        )
    }

    override fun create() {

    }

}