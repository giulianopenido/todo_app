package com.developer.todo.useCase

import com.developer.todo.base.IUseCase
import com.developer.todo.model.User
import com.developer.todo.network.schema.LoginInput
import com.developer.todo.repository.LoginRepository

class LoginUseCase(
    private val loginRepository: LoginRepository
) : IUseCase<LoginInput, User> {
    override suspend fun execute(param: LoginInput): User {
        return loginRepository.login(param.email, param.password)
    }

}

