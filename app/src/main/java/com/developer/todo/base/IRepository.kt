package com.developer.todo.base

import androidx.lifecycle.MutableLiveData

interface IRepository<T> {

    suspend fun getAll(): MutableLiveData<MutableList<T>>

}