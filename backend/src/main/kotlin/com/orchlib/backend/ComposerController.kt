package com.orchlib.backend

import com.orchlib.backend.database.ComposerDAO
import com.orchlib.backend.database.ComposerDTO
import com.orchlib.backend.database.DatabaseResponse
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

const val apiV1Root = "/api/v1"

private fun toComposerDTO(requestBody: String): ComposerDTO {
    return jacksonObjectMapper().readValue(requestBody, ComposerDTO::class.java)
}

@RestController
class ComposerController {
    private final val jdbcTemplate = JdbcTemplate()
    private final val composerDAO = ComposerDAO(jdbcTemplate)
    val composerService = ComposerService(composerDAO, jdbcTemplate)

    @GetMapping
    @RequestMapping("$apiV1Root/composers")
    fun composers(): String? {
        return "Here are our composers"
    }

    @PostMapping(
        value = ["$apiV1Root/composers"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun createComposer(@RequestBody composer: String): DatabaseResponse {
        return composerService.add(toComposerDTO(composer))
    }
}
