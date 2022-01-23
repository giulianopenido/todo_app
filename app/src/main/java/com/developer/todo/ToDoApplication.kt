package com.developer.todo

import android.app.Application
import com.developer.todo.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ToDoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin() {
            androidLogger()
            androidContext(this@ToDoApplication)
            modules(mainModule)
        }
    }
}