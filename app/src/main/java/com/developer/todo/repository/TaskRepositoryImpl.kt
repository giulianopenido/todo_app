package com.developer.todo.repository

import androidx.lifecycle.MutableLiveData
import com.developer.todo.base.IRepository
import com.developer.todo.data.SharedPreferencesService
import com.developer.todo.model.Category
import com.developer.todo.model.Task
import com.developer.todo.network.TasksApi

class TaskRepositoryImpl(
    private val tasksApi: TasksApi,
    private val sharedPreferencesService: SharedPreferencesService
): TaskRepository {

    private val userId = sharedPreferencesService.getUserId()

    override suspend fun getAll(): MutableLiveData<MutableList<Task>> {
        return MutableLiveData(
            tasksApi.getAllTasks(userId)
        )
    }


}