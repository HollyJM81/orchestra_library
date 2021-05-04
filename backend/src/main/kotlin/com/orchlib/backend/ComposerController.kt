package com.orchlib.backend

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.orchlib.backend.database.ComposerDTO
import com.orchlib.backend.database.ComposerRowMapper
import com.orchlib.backend.database.DatabaseWriteResponse
import com.orchlib.backend.database.JdbcComposerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

const val apiV1Root = "/api/v1"

private fun toComposerDTO(requestBody: String): ComposerDTO {
    return jacksonObjectMapper().readValue(requestBody, ComposerDTO::class.java)
}

@RestController
class ComposerController {
    private final val jdbcTemplate = JdbcTemplate()
    private val composerRowMapper = ComposerRowMapper()
    private final val jdbcComposerRepository = JdbcComposerRepository(jdbcTemplate, composerRowMapper)
    val composerService = ComposerService(jdbcComposerRepository, jdbcTemplate)

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
    fun saveComposer(@RequestBody composer: String): DatabaseWriteResponse {
        return composerService.add(toComposerDTO(composer))
    }
}
