package com.orchlib.backend.composer

import com.orchlib.backend.ComposerService
import com.orchlib.backend.composer.persistence.buildAddSuccess
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.sql.Date

@AutoConfigureMockMvc
@ActiveProfiles("ComposerControllerTest")
@SpringBootTest

class ComposerControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mockComposerService: ComposerService

    val beethoven = ComposerDTO(
        "Beethoven",
        "van",
        "Ludwig",
        Date.valueOf("1770-12-17")
    )
    val mendelssohn = ComposerDTO(
        "Mendelssohn",
        null,
        "Fanny",
        Date.valueOf("1820-12-17")
    )

    @Nested
    inner class GetComposers {
        @Test
        fun `getComposers returns empty list`() {
            whenever(mockComposerService.findAll()).thenReturn(listOf())
            val result: ResultActions = mockMvc.perform(get("$apiV1Root/composers")).andExpect(status().isOk)
            val response = result.andReturn().response

            val expectedResponse = "{\"results\":[]}"
            assertThat(response.contentAsString).isEqualTo(expectedResponse)
        }

        @Test
        fun `getComposers returns a list with 1 composer present`() {
            whenever(mockComposerService.findAll()).thenReturn(listOf(beethoven, mendelssohn))
            val result: ResultActions = mockMvc.perform(get("$apiV1Root/composers")).andExpect(status().isOk)
            val response = result.andReturn().response

            val expectedResponse = "{\"results\":[{\"last_name\":\"Beethoven\",\"middle_name\":\"van\",\"first_name\"" +
                ":\"Ludwig\",\"date_of_birth\":\"1770-12-17\"},{\"last_name\":\"Mendelssohn\",\"middle_name\":null," +
                "\"first_name\":\"Fanny\",\"date_of_birth\":\"1820-12-17\"}]}"
            assertThat(response.contentAsString).isEqualTo(expectedResponse)
        }
    }

    @Nested
    inner class SaveComposer {
        private val validComposerString = "{\n" +
            "       \"last_name\": \"${beethoven.last_name}\",\n" +
            "        \"first_name\": \"${beethoven.first_name}\",\n" +
            "        \"date_of_birth\": \"${beethoven.date_of_birth}\"\n" +
            "\n" +
            "}"

        @Test
        fun `saveComposer success`() {
            val addSuccess = buildAddSuccess(
                beethoven.last_name,
                "composer",
                1
            )
            whenever(mockComposerService.save(any())).thenReturn(addSuccess)
            val result: ResultActions = mockMvc.perform(
                post("$apiV1Root/composers")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(validComposerString)
            )
                .andExpect(status().isCreated)
            val response = result.andReturn().response

            val expectedResponse = "{\"writeResponse\":{\"message\":\"Inserted ${beethoven.last_name} into " +
                "composer table. Rows affected: 1.\"}}"
            assertThat(response.contentAsString).isEqualTo(expectedResponse)
        }

        @Test
        fun `saveComposer failure - bad request`() {
            val invalidComposerString = "{\n" +
                "        \"first_name\": \"${beethoven.first_name}\",\n" +
                "        \"date_of_birth\": \"${beethoven.date_of_birth}\"\n" +
                "\n" +
                "}"
            val result: ResultActions = mockMvc.perform(
                post("$apiV1Root/composers")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(invalidComposerString)
            )
                .andExpect(status().isBadRequest)
            val response = result.andReturn().response

            val expectedResponse = "{\"error\":\"Instantiation of [simple type, " +
                "class com.orchlib.backend.composer.ComposerDTO] value failed for JSON property last_name due to " +
                "missing (therefore NULL) value for creator parameter last_name which is a non-nullable type\\n at " +
                "[Source: (String)\\\"{\\n        \\\"first_name\\\": \\\"Ludwig\\\",\\n        \\\"date_of_birth\\\": " +
                "\\\"1770-12-17\\\"\\n\\n}\\\"; line: 5, column: 1] (through reference chain: " +
                "com.orchlib.backend.composer.ComposerDTO[\\\"last_name\\\"])\"}"
            assertThat(response.contentAsString).isEqualTo(expectedResponse)
        }
    }
}

@Profile("ComposerControllerTest")
@SpringBootConfiguration
class ComposerControllerTestConfiguration {
    @Bean
    @Primary
    fun mockComposerService(): ComposerService {
        return mock()
    }
}
