package com.developer.todo.model

data class Task(
    var name: String,
    var description: String,
    var category: Category,
    var isDone: Boolean = false
) {

}