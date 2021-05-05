package com.orchlib.backend.composer

import java.sql.Date

interface DTO

data class ComposerDTO(
    val last_name: String,
    val middle_name: String? = null,
    val first_name: String,
    val date_of_birth: Date
) : DTO
