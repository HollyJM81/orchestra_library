package com.orchlib.backend.database

import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.jdbc.core.JdbcTemplate
import java.lang.RuntimeException
import java.sql.Date

class JdbcComposerTest {
    private val composerDTO = ComposerDTO(
        last_name = "Mendelssohn",
        first_name = "Fanny",
        date_of_birth = Date.valueOf("2020-01-31")
    )
    private lateinit var mockJdbcTemplate: JdbcTemplate
    private lateinit var jdbcComposerRepository: JdbcComposerRepository
    private val composerRowMapper = ComposerRowMapper()

    @BeforeEach
    fun setUp() {
        mockJdbcTemplate = mock()
//        jdbcComposerRepository = JdbcComposerRepository(mockJdbcTemplate, composerRowMapper)
    }

    @Nested
    inner class FindAll {
        @Test
        fun `find all composers returns results`() {
            val composers: List<ComposerDTO> = listOf(
                composerDTO,
                ComposerDTO(
                    last_name = "Schumann",
                    first_name = "Clara",
                    date_of_birth = Date.valueOf("1884-02-02")
                )
            )
            whenever(
                mockJdbcTemplate.query(
                    "select * from Composer",
                    composerRowMapper
                )
            ).thenReturn(composers)
            val actual = jdbcComposerRepository.findAll()
            assertEquals(composers, actual)
        }

        @Test
        fun `find all composers returns empty list`() {
            val actual = jdbcComposerRepository.findAll()
            assertEquals(listOf<ComposerDTO>(), actual)
        }
    }

    @Nested
    inner class FindOne {
        private val id = 1
        @Test
        fun `find one composer returns result`() {
            whenever(
                mockJdbcTemplate.queryForObject(
                    "select * from Composer where id=?",
                    composerRowMapper,
                    id
                )
            ).thenReturn(composerDTO)
            val actual = jdbcComposerRepository.findOne(id)
            assertEquals(composerDTO, actual)
        }

        @Test
        fun `find one composer returns null`() {
            val actual = jdbcComposerRepository.findOne(id)
            val expected = null
            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class Save {
        @Test
        fun `save composer success`() {
            val numberOfRowsAffected = 1
            whenever(
                mockJdbcTemplate.update(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            ).thenReturn(numberOfRowsAffected)
            val actual = jdbcComposerRepository.save(composerDTO)
            val expected = buildAddSuccess(numberOfRowsAffected)
            assertEquals(expected, actual)
        }

        @Test
        fun `save composer failure`() {
            whenever(
                mockJdbcTemplate.update(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            ).thenThrow(RuntimeException("message"))
            val actual = jdbcComposerRepository.save(composerDTO)
            val expected = buildAddFailure(composerDTO.last_name, "composer", null)
            assertEquals(expected, actual)
        }
    }
}
