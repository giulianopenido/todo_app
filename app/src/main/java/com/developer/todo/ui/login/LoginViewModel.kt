package com.developer.todo.ui.login

import com.developer.todo.base.BaseViewModel
import com.developer.todo.data.SharedPreferencesService
import com.developer.todo.helper.SingleLiveEvent
import com.developer.todo.model.User
import com.developer.todo.network.schema.LoginInput
import com.developer.todo.useCase.LoginUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val sharedPreferencesService: SharedPreferencesService
) : BaseViewModel() {

    val sucessfulLogin = SingleLiveEvent<Unit>()

    fun login(email: String, password: String) {
        doAsyncWork(shouldShowLoading = true) {
            val user = loginUseCase.execute(LoginInput(email, password))
            saveUserData(user)
            sucessfulLogin.call()
        }
    }

    private fun saveUserData(user: User) =
        sharedPreferencesService.let {
            it.setToken(user.token)
            it.setName(user.name)
            it.setEmail(user.email)
        }

}