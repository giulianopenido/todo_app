package com.developer.todo.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developer.todo.model.Category
import com.developer.todo.model.Task
import com.developer.todo.repository.CategoryRepository
import com.developer.todo.repository.TaskRepository
import java.util.*

class HomeViewModel : ViewModel() {

    var categories : MutableLiveData<MutableList<Category>>
    var tasks : MutableLiveData<MutableList<Task>>
    private var categoryRepository : CategoryRepository
    private var taskRepository : TaskRepository


    init {
        categoryRepository = CategoryRepository()
        categories = categoryRepository.getAll()

        taskRepository = TaskRepository()
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