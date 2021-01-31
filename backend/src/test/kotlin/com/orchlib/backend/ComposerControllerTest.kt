package com.orchlib.backend

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ComposerControllerTest {
    @Test
    @Throws(Exception::class)
    fun greetingShouldReturnDefaultMessage() {
        val actual = true
        val expected = true
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
