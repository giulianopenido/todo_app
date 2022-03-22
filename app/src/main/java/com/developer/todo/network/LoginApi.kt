package com.developer.todo.network

import com.developer.todo.network.schema.LoginInput
import com.developer.todo.network.schema.UserSchema
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("auth")
    suspend fun login(@Body loginInput: LoginInput): Response<UserSchema>
}