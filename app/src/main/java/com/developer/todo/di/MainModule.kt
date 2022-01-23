package com.developer.todo.di

import com.developer.todo.repository.CategoryRepository
import com.developer.todo.repository.CategoryRepositoryImpl
import com.developer.todo.repository.TaskRepository
import com.developer.todo.repository.TaskRepositoryImpl
import com.developer.todo.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single<TaskRepository> {
        TaskRepositoryImpl()
    }

    single<CategoryRepository> {
        CategoryRepositoryImpl()
    }

    viewModel {
        HomeViewModel(
            get(),
            get()
        )
    }
}