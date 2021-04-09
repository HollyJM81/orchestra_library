package com.orchlib.backend

import com.orchlib.backend.database.ComposerDAO
import com.orchlib.backend.database.ComposerDTO
import org.springframework.jdbc.core.JdbcTemplate
import com.orchlib.backend.database.DatabaseResponse
import org.springframework.jdbc.datasource.DriverManagerDataSource


class ComposerService(
    private val composerDAO: ComposerDAO,
    private val jdbcTemplate: JdbcTemplate
) {
    fun add(composer: ComposerDTO): DatabaseResponse {
        setDataSourceHackily()
        return composerDAO.add(composer)
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