package com.developer.todo.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developer.todo.model.Category
import com.developer.todo.model.Task
import com.developer.todo.repository.CategoryRepository
import com.developer.todo.repository.TaskRepository
import java.util.*

class HomeViewModel(
    private val taskRepository : TaskRepository,
    private val categoryRepository : CategoryRepository
) : ViewModel() {

    var categories : MutableLiveData<MutableList<Category>>
    var tasks : MutableLiveData<MutableList<Task>>

    init {
        categories = categoryRepository.getAll()
        tasks = taskRepository.getAll()
    }

    fun toggleIsDone(position: Int) {
        tasks.value!![position].isDone = !tasks.value!![position].isDone
    }

    fun deleteTask(position: Int) {
        tasks.value!!.removeAt(position)
    }

    fun moveTask(from: Int, to: Int) {
        Collections.swap(tasks.value!!, from, to)
    }

}