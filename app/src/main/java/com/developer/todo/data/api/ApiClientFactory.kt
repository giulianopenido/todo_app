package com.developer.todo.data.api

import com.developer.todo.BuildConfig
import com.developer.todo.data.SharedPreferencesService
import okhttp3.OkHttpClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientFactory : KoinComponent{

    private val sharedPreferencesService: SharedPreferencesService = get()

    fun <T> create(
        serviceClass: Class<T>,
        baseUrl: String = BuildConfig.BASE_API_URL,
        useAuthInterceptor: Boolean = true,
    ): T {
        val clientBuilder = OkHttpClient().newBuilder()

        if(useAuthInterceptor) {
            val authToken = sharedPreferencesService.getToken()
            clientBuilder.addInterceptor(AuthInterceptor(authToken))
        }

        val client =  clientBuilder.build()
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }
}