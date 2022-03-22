package com.developer.todo.repository

import com.developer.todo.model.User

interface LoginRepository {
    suspend fun login(email: String, password: String): User
}