package com.developer.todo.base

interface ISchema<T> {
    fun toModel() : T
}