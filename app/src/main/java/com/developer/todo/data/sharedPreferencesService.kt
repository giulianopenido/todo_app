package com.developer.todo.data

import android.content.SharedPreferences

class SharedPreferencesService(
    private val sharedPreferences: SharedPreferences
) {

    fun setUserId(id: Int) = sharedPreferences.edit().putInt("user_id", id).commit()

    fun getUserId() = sharedPreferences.getInt("user_id", 0)

    fun setToken(token: String) = sharedPreferences.edit().putString("auth_token", token).commit()

    fun getToken() = sharedPreferences.getString("auth_token", null)

    fun setEmail(email: String) = sharedPreferences.edit().putString("user_email", email).commit()

    fun setName(name: String) = sharedPreferences.edit().putString("user_name", name).commit()

    fun getName() = sharedPreferences.getString("user_name", null)
}