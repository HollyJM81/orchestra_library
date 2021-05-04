package com.orchlib.backend

import com.orchlib.backend.database.ComposerDTO
import com.orchlib.backend.database.DatabaseWriteResponse
import com.orchlib.backend.database.JdbcComposerRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource

class ComposerService(
    private val composerDAO: JdbcComposerRepository,
    private val jdbcTemplate: JdbcTemplate
) {
    fun add(composer: ComposerDTO): DatabaseWriteResponse {
        setDataSourceHackily()
        return composerDAO.save(composer)
    }

    private fun setDataSourceHackily() {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("org.postgresql.Driver")
        dataSource.url = "jdbc:postgresql://localhost:5432/paulamuldoon"
        dataSource.username = "dev"
        dataSource.password = "dev_database_password"

        jdbcTemplate.dataSource = dataSource
    }
}
