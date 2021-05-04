package com.orchlib.backend.composer

import com.orchlib.backend.composer.persistence.ComposerRowMapper
import com.orchlib.backend.composer.persistence.JdbcComposerRepository
import com.orchlib.backend.composer.persistence.buildAddFailure
import com.orchlib.backend.composer.persistence.buildAddSuccess
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import java.lang.RuntimeException
import java.sql.Date

@ActiveProfiles("JdbcComposerRepositoryTest")
@SpringBootTest
class JdbcComposerRepositoryTest {
    private val composerDTO = ComposerDTO(
        last_name = "Mendelssohn",
        first_name = "Fanny",
        date_of_birth = Date.valueOf("2020-01-31")
    )
    @Autowired
    private lateinit var mockJdbcTemplate: JdbcTemplate

    @Autowired
    private lateinit var testComposerRowMapper: ComposerRowMapper

    @Autowired
    private lateinit var testJdbcComposerRepository: JdbcComposerRepository

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
                    testComposerRowMapper
                )
            ).thenReturn(composers)
            val actual = testJdbcComposerRepository.findAll()
            assertThat(actual).isEqualTo(composers)
        }

        @Test
        fun `find all composers returns empty list`() {
            whenever(
                mockJdbcTemplate.query(
                    "select * from Composer",
                    testComposerRowMapper
                )
            ).thenReturn(listOf())
            val actual = testJdbcComposerRepository.findAll()
            assertThat(actual).isEqualTo(listOf<ComposerDTO>())
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
                    testComposerRowMapper,
                    id
                )
            ).thenReturn(composerDTO)
            val actual = testJdbcComposerRepository.findById(id)
            assertThat(actual).isEqualTo(composerDTO)
        }

        @Test
        fun `find one composer returns null`() {
            whenever(
                mockJdbcTemplate.queryForObject(
                    "select * from Composer where id=?",
                    testComposerRowMapper,
                    id
                )
            ).thenReturn(null)
            val actual = testJdbcComposerRepository.findById(id)
            val expected = null
            assertThat(actual).isEqualTo(expected)
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
            val actual = testJdbcComposerRepository.save(composerDTO)
            val expected = buildAddSuccess(
                composerDTO.last_name,
                "composer",
                numberOfRowsAffected
            )
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `save composer failure`() {
            val message = "too much chocolate"
            whenever(
                mockJdbcTemplate.update(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            ).thenThrow(RuntimeException(message))
            val actual = testJdbcComposerRepository.save(composerDTO)
            val expected = buildAddFailure(composerDTO.last_name, "composer", message)
            assertThat(actual).isEqualTo(expected)
        }
    }
}

@Profile("JdbcComposerRepositoryTest")
@SpringBootConfiguration
class JdbcComposerRepositoryTestConfiguration {
    @Bean
    @Primary
    fun mockJdbcTemplate(): JdbcTemplate {
        return mock()
    }

    @Bean
    @Primary
    fun testComposerRowMapper(): ComposerRowMapper {
        return ComposerRowMapper()
    }

    @Bean
    fun testJdbcComposerRepository(): JdbcComposerRepository {
        return JdbcComposerRepository()
    }
}
