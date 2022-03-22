package com.developer.todo.network.schema

import com.developer.todo.base.ISchema
import com.developer.todo.model.User

data class UserSchema(
    val id: Int,
    val name: String,
    val email: String,
    val token: String
) : ISchema<User> {
    override fun toModel(): User {
        return User(id, name, email, token)
    }

}