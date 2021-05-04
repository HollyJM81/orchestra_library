package com.orchlib.backend

import com.orchlib.backend.database.ComposerDTO
import com.orchlib.backend.database.JdbcComposerRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.sql.Date

@AutoConfigureMockMvc
@ActiveProfiles("ComposerControllerTest")
@SpringBootTest(classes = [TestApplication::class])

class ComposerControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var mockJdbcComposerRepository: JdbcComposerRepository

    @Nested
    inner class GetComposers {
        @Test
        fun `getComposers returns empty list`() {
            whenever(mockJdbcComposerRepository.findAll()).thenReturn(listOf())
            val result: ResultActions = mockMvc.perform(get("$apiV1Root/composers")).andExpect(status().isOk)
            val response = result.andReturn().response

            val expectedResponse = "{\"results\":[]}"
            assertThat(response.contentAsString).isEqualTo(expectedResponse)
        }

        @Test
        fun `getComposers returns a list with 1 composer present`() {
            val beethoven = ComposerDTO(
                "Beethoven", "van", "Ludwig", Date.valueOf("1770-12-17")
            )
            val mendelssohn = ComposerDTO(
                "Mendelssohn", null, "Fanny", Date.valueOf("1820-12-17")
            )
            whenever(mockJdbcComposerRepository.findAll()).thenReturn(
                listOf(
                    beethoven,
                    mendelssohn
                )
            )
            val result: ResultActions = mockMvc.perform(get("$apiV1Root/composers")).andExpect(status().isOk)
            val response = result.andReturn().response

            val expectedResponse = "{\"results\":[{\"last_name\":\"Beethoven\",\"middle_name\":\"van\",\"first_name\"" +
                ":\"Ludwig\",\"date_of_birth\":\"1770-12-17\"},{\"last_name\":\"Mendelssohn\",\"middle_name\":null," +
                "\"first_name\":\"Fanny\",\"date_of_birth\":\"1820-12-17\"}]}"
            assertThat(response.contentAsString).isEqualTo(expectedResponse)
        }
    }
}

@Profile("ComposerControllerTest")
@SpringBootConfiguration
class ComposerControllerTestConfiguration {
    @Bean
    @Primary
    fun mockJdbcComposerRepository(): JdbcComposerRepository {
        return mock()
    }
}