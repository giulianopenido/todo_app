package com.developer.todo

import android.app.Application
import com.developer.todo.di.apiModule
import com.developer.todo.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ToDoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin() {
            androidLogger(Level.ERROR)
            androidContext(this@ToDoApplication)
            modules(listOf(mainModule, apiModule))
        }
    }
}