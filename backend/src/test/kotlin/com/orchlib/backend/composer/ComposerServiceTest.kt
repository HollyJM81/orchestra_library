package com.orchlib.backend.composer

import com.orchlib.backend.ComposerService
import com.orchlib.backend.composer.persistence.JdbcComposerRepository
import com.orchlib.backend.composer.persistence.buildAddFailure
import com.orchlib.backend.composer.persistence.buildAddSuccess
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ActiveProfiles
import java.sql.Date

@ActiveProfiles("ComposerServiceTest")
@SpringBootTest

class ComposerServiceTest {
    @Autowired
    private lateinit var mockJdbcComposerRepository: JdbcComposerRepository

    @Autowired
    private lateinit var composerService: ComposerService

    val composerDTO = ComposerDTO(
        last_name = "Beethoven",
        middle_name = "van",
        first_name = "Ludwig",
        date_of_birth = Date.valueOf("1770-12-17")
    )

    val composerDTOHildegard = ComposerDTO(
        last_name = "Bingen",
        middle_name = "von",
        first_name = "Hildegard",
        date_of_birth = Date.valueOf("1010-10-10")
    )

    @Nested
    inner class FindAll {
        @Test
        fun `findAll returns empty list`() {
            whenever(mockJdbcComposerRepository.findAll()).thenReturn(listOf())
            val expected = listOf<ComposerDTO>()
            val actual = composerService.findAll()
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `findAll returns list of composers`() {
            whenever(mockJdbcComposerRepository.findAll()).thenReturn(
                listOf(
                    composerDTO,
                    composerDTOHildegard
                )
            )
            val expected = listOf(composerDTO, composerDTOHildegard)
            val actual = composerService.findAll()
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Nested
    inner class FindById {
        @Test
        fun `findById returns null`() {
            whenever(mockJdbcComposerRepository.findById(1)).thenReturn(
                null
            )
            val expected = null
            val actual = composerService.findOne(1)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `findById returns composer`() {
            whenever(mockJdbcComposerRepository.findById(1)).thenReturn(
                composerDTO
            )
            val expected = composerDTO
            val actual = composerService.findOne(1)
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Nested
    inner class Save {
        @Test
        fun `save returns success response`() {
            whenever(mockJdbcComposerRepository.save(composerDTO)).thenReturn(
                buildAddSuccess(
                    composerDTO.last_name,
                    "composer",
                    1
                )
            )

            val expected = buildAddSuccess(
                composerDTO.last_name,
                "composer",
                1
            )

            val actual = composerService.save(composerDTO)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `save returns failure response`() {
            whenever(mockJdbcComposerRepository.save(composerDTO)).thenReturn(
                buildAddFailure(
                    composerDTO.last_name,
                    "composer",
                    "Error"
                )
            )

            val expected = buildAddFailure(
                composerDTO.last_name,
                "composer",
                "Error"
            )

            val actual = composerService.save(composerDTO)
            assertThat(actual).isEqualTo(expected)
        }
    }
}

@Profile("ComposerServiceTest")
@SpringBootConfiguration
class ComposerServiceTestConfiguration {
    @Bean
    @Primary
    fun mockJdbcComposerRepository(): JdbcComposerRepository {
        return mock()
    }

    @Bean
    fun testComposerService(): ComposerService {
        return ComposerService()
    }
}
