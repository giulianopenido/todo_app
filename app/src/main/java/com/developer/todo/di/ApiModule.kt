package com.developer.todo.di

import com.developer.todo.data.api.ApiClientFactory
import com.developer.todo.network.LoginApi
import com.developer.todo.network.TasksApi
import org.koin.dsl.module

val apiModule = module {

    single {
        ApiClientFactory.create(LoginApi::class.java)
    }

    single {
        ApiClientFactory.create(TasksApi::class.java)
    }


}