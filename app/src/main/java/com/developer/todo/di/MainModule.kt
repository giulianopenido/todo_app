package com.developer.todo.di

import android.content.Context
import android.content.SharedPreferences
import com.developer.todo.data.SharedPreferencesService
import com.developer.todo.repository.*
import com.developer.todo.ui.home.HomeViewModel
import com.developer.todo.ui.login.LoginViewModel
import com.developer.todo.ui.splash.SplashViewModel
import com.developer.todo.useCase.GetAllTasksUseCase
import com.developer.todo.useCase.LoginUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single<TaskRepository> {
        TaskRepositoryImpl(
            tasksApi = get(),
            sharedPreferencesService = get()
        )
    }

    single<CategoryRepository> {
        CategoryRepositoryImpl()
    }

    single<LoginRepository> {
        LoginRepositoryImpl(loginApi = get())
    }

    single {
        SharedPreferencesService(
            androidContext().getSharedPreferences("TodoApp", Context.MODE_PRIVATE)
        )
    }

    viewModel {
        HomeViewModel(
            get(),
        )
    }

    viewModel {
        SplashViewModel(
            get()
        )
    }

    viewModel {
        LoginViewModel (
            loginUseCase = get(),
            sharedPreferencesService = get()
        )
    }

    single { LoginUseCase(loginRepository = get()) }

    single { GetAllTasksUseCase(taskRepository = get()) }

}