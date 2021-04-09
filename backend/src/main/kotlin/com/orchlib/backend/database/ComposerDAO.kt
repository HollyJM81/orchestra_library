package com.orchlib.backend.database

import org.springframework.jdbc.core.JdbcTemplate

class ComposerDAO(private val jdbcTemplate: JdbcTemplate) {
    fun add(composerDTO: ComposerDTO): DatabaseResponse {
        createComposerTableIfNotExists()
        val id: Int = jdbcTemplate.update(
            "insert into COMPOSER (last_name, middle_name, first_name, date_of_birth) values(?, ?, ?, ?)",
            composerDTO.last_name,
            composerDTO.middle_name,
            composerDTO.first_name,
            composerDTO.date_of_birth
        )
        return AddSuccess(id = id)
    }

    private fun createComposerTableIfNotExists() {
        jdbcTemplate.execute(
            "CREATE TABLE IF NOT EXISTS COMPOSER\n" +
                "(\n" +
                "    ID            serial,\n" +
                "    FIRST_NAME    varchar(255),\n" +
                "    MIDDLE_NAME   varchar(255),\n" +
                "    LAST_NAME     varchar(255),\n" +
                "    DATE_OF_BIRTH date\n" +
                ");"
        )
    }
}
