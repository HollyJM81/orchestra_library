package com.orchlib.backend.database

import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import javax.sql.DataSource

class ComposerTest {

    @Nested
    inner class AddComposerSuccess {
        val composerDAO = ComposerDAO()
        val embeddedDatabaseBuilder = EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
        val dataSource: DataSource = embeddedDatabaseBuilder
            .addScript("classpath:jdbc/schema.sql")
            .build()
        @BeforeEach
        fun setUp() {
            composerDAO.setDataSource(dataSource)
        }
        @Test
        fun `add composer returns success`() {
            val composerToAdd = ComposerDTO(
                last_name = "Mendelssohn",
                first_name = "Fanny",
                date_of_birth = "2020-01-31"
            )
            val actual = composerDAO.add(composerToAdd)
            val expected = AddSuccess(id = 1)
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `add composer returns failure`() {
        val composerToAdd = ComposerDTO(
            last_name = "Mendelssohn",
            first_name = "Fanny",
            date_of_birth = "2020-01-31"
        )
        val composerDAO = ComposerDAO()
        val actual = composerDAO.add(composerToAdd)
        val expected = AddFailure("Error inserting composer with last name Mendelssohn into database.")
        assertEquals(expected, actual)
    }
}
