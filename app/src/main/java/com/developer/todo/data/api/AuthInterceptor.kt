package com.developer.todo.data.api

import com.developer.todo.data.SharedPreferencesService
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val authToken: String?
): Interceptor  {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain
            .request()
            .newBuilder()
            .header("Authorization", "Bearer $authToken")
            .build()

        val response = chain.proceed(request)

        if(response.code == 401) {
            //TODO: Logout
        }

        return response
    }

}