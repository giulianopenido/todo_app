package com.developer.todo.model

data class Category(
    var id: Int,
    var name: String,
    var numOfTasks: Int,
    var completedTasks: Int,
    var color: String,
)
