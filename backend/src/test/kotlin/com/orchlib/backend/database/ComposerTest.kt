package com.orchlib.backend.database

import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.jdbc.core.JdbcTemplate
import java.lang.RuntimeException

class ComposerTest {
    private val composerToAdd = ComposerDTO(
        last_name = "Mendelssohn",
        first_name = "Fanny",
        date_of_birth = "2020-01-31"
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
        val id = 0
        whenever(mockJdbcTemplate.update(any(), any(), any(), any())).thenReturn(id)
        val actual = composerDAO.add(composerToAdd)
        val expected = AddSuccess(id = id)
        assertEquals(expected, actual)
    }

    @Test
    fun `add composer returns failure`() {
        val errorMessage = "Error inserting composer with last name Mendelssohn into database."
        val exception = RuntimeException(errorMessage)
        whenever(mockJdbcTemplate.update(any(), any(), any(), any())).thenThrow(exception)
        val exceptionThrown = assertThrows<RuntimeException> { composerDAO.add(composerToAdd) }
        assertEquals(exceptionThrown.message, exception.message)
    }
}
