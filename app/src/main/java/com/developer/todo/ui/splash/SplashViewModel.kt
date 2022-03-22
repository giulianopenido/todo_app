package com.developer.todo.ui.splash

import androidx.lifecycle.ViewModel
import com.developer.todo.data.SharedPreferencesService

class SplashViewModel(
    private val sharedPreferencesService: SharedPreferencesService
) : ViewModel() {

    fun isAuthenticated(): Boolean {
        return sharedPreferencesService.getToken() != null
    }

}