package com.orchlib.backend.database

data class ComposerDTO(
//    val id = ("id").autoIncrement()
    val last_name: String,
    val middle_name: String? = null,
    val first_name: String,
    val date_of_birth: String
)

