package com.orchlib.backend.database

import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import javax.sql.DataSource

class ComposerTest {
    private val embeddedDatabaseBuilder = EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)

    @Test
    fun greetingShouldReturnDefaultMessage() {
//        val composer = ComposerDAO()
//        val composerToAdd = ComposerDTO(
//            last_name = "Mendelssohn",
//            first_name = "Fanny",
//            date_of_birth = "Dec 12, 1888"
//        )
//        val actual = composer.add(composerToAdd)
//        val expected = DatabaseResponse(
//            databaseResponse = DatabaseResponseType.SUCCESS,
//            id = 0
//        )
//        assertEquals(expected, actual)
    }

    @Test
    fun `get count of composers returns correct number`() {
        val dataSource: DataSource = embeddedDatabaseBuilder
            .addScript("classpath:jdbc/schema.sql")
            .addScript("classpath:jdbc/test-data.sql")
            .build()
        val composerDAO = ComposerDAO()

        composerDAO.setDataSource(dataSource)

        val actual = composerDAO.getCountOfComposers()
        val expected = 1
        assertEquals(expected, actual)
    }
}
