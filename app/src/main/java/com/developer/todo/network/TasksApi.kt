package com.developer.todo.network

import com.developer.todo.model.Task
import retrofit2.http.GET
import retrofit2.http.Path

interface TasksApi {
    @GET("users/{id}/tasks")
    suspend fun getAllTasks(@Path("id") id: Int) : MutableList<Task>
}