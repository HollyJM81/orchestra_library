package com.orchlib.backend.database

import org.springframework.jdbc.core.JdbcTemplate

class ComposerDAO(private val jdbcTemplate: JdbcTemplate) {
    fun add(composerDTO: ComposerDTO): DatabaseResponse {
        val id: Int = jdbcTemplate.update(
            "insert into COMPOSER (last_name, first_name, date_of_birth) values(?, ?, ?)",
            composerDTO.last_name,
            composerDTO.first_name,
            composerDTO.date_of_birth
        )
        return AddSuccess(id = id)
    }
}
