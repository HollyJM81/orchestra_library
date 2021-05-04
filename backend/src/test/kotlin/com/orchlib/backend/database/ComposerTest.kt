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

class ComposerTest {
    private val composerToAdd = ComposerDTO(
        last_name = "Mendelssohn",
        first_name = "Fanny",
        date_of_birth = Date.valueOf("2020-01-31")
    )
    private lateinit var mockJdbcTemplate: JdbcTemplate
    private lateinit var composerDAO: ComposerDAO

    @BeforeEach
    fun setUp() {
        mockJdbcTemplate = mock()
        composerDAO = ComposerDAO(mockJdbcTemplate)
    }

    @Test
    fun `add composer returns success`() {
        val numberOfRowsAffected = 1
        whenever(mockJdbcTemplate.update(
            any(),
            any(),
            any(),
            any(),
            any()
        )).thenReturn(numberOfRowsAffected)
        val actual = composerDAO.add(composerToAdd)
        val expected = buildAddSuccess(numberOfRowsAffected)
        assertEquals(expected, actual)
    }

    @Test
    fun `add composer returns failure`() {
        val exception = RuntimeException("message")
        whenever(mockJdbcTemplate.update(
            any(),
            any(),
            any(),
            any(),
            any()
        )).thenThrow(exception)
        val actual = composerDAO.add(composerToAdd)
        val expected = buildAddFailure(composerToAdd.last_name)
        assertEquals(expected, actual)
    }
}
