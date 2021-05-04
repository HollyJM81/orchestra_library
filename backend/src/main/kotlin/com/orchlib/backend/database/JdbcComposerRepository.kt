package com.orchlib.backend.database

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class JdbcComposerRepository(
    private val jdbcTemplate: JdbcTemplate,
    private val composerRowMapper: ComposerRowMapper
) : ComposerRepository {

    override fun findOne(id: Int): ComposerDTO? {
        return jdbcTemplate.queryForObject(
            "select * from Composer where id=?",
            composerRowMapper,
            id
        )
    }

    override fun save(composerDTO: ComposerDTO): DatabaseWriteResponse {
        val numberOfRowsAffected: Int = try {
            createComposerTableIfNotExists()
            insertComposer(composerDTO)
        } catch (exception: Exception) {
            return buildAddFailure(composerDTO.last_name, "composer")
        }
        return buildAddSuccess(numberOfRowsAffected)
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

    private fun insertComposer(composerDTO: ComposerDTO): Int {
        return jdbcTemplate.update(
            "insert into COMPOSER (last_name, middle_name, first_name, date_of_birth) values(?, ?, ?, ?)",
            composerDTO.last_name,
            composerDTO.middle_name,
            composerDTO.first_name,
            composerDTO.date_of_birth
        )
    }

    override fun findAll(): ComposerDTO {
        TODO("Not yet implemented")
    }
}
