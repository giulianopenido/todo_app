package com.developer.todo.base

import androidx.lifecycle.MutableLiveData

interface IRepository<T> {

    fun getAll(): MutableLiveData<MutableList<T>>
    fun create()

}