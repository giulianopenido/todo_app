package com.developer.todo.ui.home

import androidx.lifecycle.MutableLiveData
import com.developer.todo.base.BaseViewModel
import com.developer.todo.helper.SingleLiveEvent
import com.developer.todo.model.Category
import com.developer.todo.model.Task
import com.developer.todo.repository.CategoryRepository
import com.developer.todo.repository.TaskRepository
import com.developer.todo.useCase.GetAllTasksUseCase

class HomeViewModel(
    getAllTasksUseCase: GetAllTasksUseCase
) : BaseViewModel() {


    lateinit var categories : MutableLiveData<MutableList<Category>>
    lateinit var tasks : MutableLiveData<MutableList<Task>>

    fun getAllTasks() {
        doAsyncWork {

        }
    }

    fun toggleIsDone(position: Int) {
        tasks.value!![position].isDone = !tasks.value!![position].isDone
    }

    fun deleteTask(position: Int) {
        tasks.value!!.removeAt(position)
    }

    fun moveTask(from: Int, to: Int) {
        tasks.value!!.apply {
            add(to, removeAt(from))
        }
    }

}