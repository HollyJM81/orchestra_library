package com.orchlib.backend.database

data class DatabaseResponse(
    val databaseResponse: DatabaseResponseType,
    val id: Int? = null,
    val message: String? = null
)

enum class DatabaseResponseType {
    SUCCESS, FAILURE
}