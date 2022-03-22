package com.developer.todo.helper

import retrofit2.Response
import java.lang.Exception

object RequestManager {

    suspend fun <T> requestFromApi(request: suspend () -> Response<T>): T? {
        val response = request()
        if(response.isSuccessful) {
            return if(response.code() == HttpCode.NO_CONTENT) {
                null
            } else {
                response.body()
            }
        } else {
            //TODO Usar exceções menos genéricas
            throw Exception()
        }

    }
}

object HttpCode {
    const val NO_CONTENT = 204
}