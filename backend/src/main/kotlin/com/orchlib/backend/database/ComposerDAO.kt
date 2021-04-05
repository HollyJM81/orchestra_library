package com.orchlib.backend.database

import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

class ComposerDAO {
    private var jdbcTemplate: JdbcTemplate? = null

    fun setDataSource(dataSource: DataSource?) {
        jdbcTemplate = dataSource?.let { JdbcTemplate(it) }
    }

    fun add(composerDTO: ComposerDTO): DatabaseResponse {
        val id: Int? = jdbcTemplate?.update(
            "insert into COMPOSER (last_name, first_name, date_of_birth) values(?, ?, ?)",
            composerDTO.last_name,
            composerDTO.first_name,
            composerDTO.date_of_birth
        )
        return if (id != null) { AddSuccess(id = id) } else
            AddFailure("Error inserting composer with last name ${composerDTO.last_name} into database.")
    }
}
