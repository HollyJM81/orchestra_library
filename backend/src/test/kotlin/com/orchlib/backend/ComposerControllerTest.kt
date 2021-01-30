package com.orchlib.backend

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

class ComposerControllerTest {
    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    @Throws(Exception::class)
    fun greetingShouldReturnDefaultMessage() {
        val actual = restTemplate.withBasicAuth("user", "musikverein").getForObject("http://localhost:$port/composers", String::class.java)
        val expected = "Here are our composers"
        Assertions.assertThat(actual).contains(expected)
    }
}
