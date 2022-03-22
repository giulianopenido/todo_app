package com.developer.todo.repository

import com.developer.todo.helper.RequestManager
import com.developer.todo.model.User
import com.developer.todo.network.LoginApi
import com.developer.todo.network.schema.LoginInput
import java.lang.Exception

class LoginRepositoryImpl(
    private val loginApi: LoginApi
) : LoginRepository {
    override suspend fun login(email: String, password: String): User {

        return RequestManager.requestFromApi { loginApi.login(LoginInput(email, password)) }?.toModel()
            ?: throw Exception()    //TODO Usar exceções menos genéricas

    }
}