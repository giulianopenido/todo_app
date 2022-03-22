package com.developer.todo.base

interface IUseCase<Params, Return> {
    suspend fun execute(param: Params): Return
}