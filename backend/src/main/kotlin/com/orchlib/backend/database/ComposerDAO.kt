package com.orchlib.backend.database

import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

class ComposerDAO {
    private var jdbcTemplate: JdbcTemplate? = null

    fun setDataSource(dataSource: DataSource?) {
        jdbcTemplate = dataSource?.let { JdbcTemplate(it) }
    }

    fun getCountOfComposers(): Int {
        return jdbcTemplate?.queryForObject("SELECT COUNT(*) FROM COMPOSER", Int::class.java)!!
    }

    fun add() {
//        transaction {
//            addLogger(StdOutSqlLogger)
//            SchemaUtils.create(ComposerDTO)
//            composerDTO.insert { record ->
//                record[last_name] = composerDTO.last_name
//                record[middle_name] = composerDTO.middle_name
//                record[first_name] = composerDTO.first_name
//                record[date_of_birth] = composerDTO.date_of_birth
//            }
//        }
    }
}