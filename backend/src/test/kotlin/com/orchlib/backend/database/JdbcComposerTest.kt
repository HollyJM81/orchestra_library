package com.orchlib.backend.database

import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.jdbc.core.JdbcTemplate
import java.lang.RuntimeException
import java.sql.Date

class JdbcComposerTest {
    private val composerToAdd = ComposerDTO(
        last_name = "Mendelssohn",
        first_name = "Fanny",
        date_of_birth = Date.valueOf("2020-01-31")
    )
    private lateinit var mockJdbcTemplate: JdbcTemplate
    private lateinit var composerDAO: JdbcComposerRepository

    @BeforeEach
    fun setUp() {
        mockJdbcTemplate = mock()
        composerDAO = JdbcComposerRepository(mockJdbcTemplate)
    }

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
        val actual = composerDAO.save(composerToAdd)
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
        val actual = composerDAO.save(composerToAdd)
        val expected = buildAddFailure(composerToAdd.last_name, "composer")
        assertEquals(expected, actual)
    }
}
