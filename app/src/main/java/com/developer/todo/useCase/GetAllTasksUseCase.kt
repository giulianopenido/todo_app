package com.developer.todo.useCase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developer.todo.base.IUseCase
import com.developer.todo.model.Task

import com.developer.todo.repository.TaskRepository
import java.time.LocalDate

class GetAllTasksUseCase(
    private val taskRepository: TaskRepository
) : IUseCase<Params, MutableLiveData<MutableList<Task>>> {

    override suspend fun execute(param: Params): MutableLiveData<MutableList<Task>> {
        return taskRepository.getAll()
    }
}

data class Params(
    val date: LocalDate
)