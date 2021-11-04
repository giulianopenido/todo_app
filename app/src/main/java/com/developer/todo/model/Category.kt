package com.developer.todo.model

data class Category(
    val name: String,
    val numOfTasks: Int,
    val completedTasks: Int,
    val color: String
)
