package com.orchlib.backend

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.orchlib.backend.database.ComposerDTO
import com.orchlib.backend.database.DatabaseWriteResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
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
    @Autowired
    private lateinit var composerService: ComposerService

    @GetMapping
    @RequestMapping("$apiV1Root/composers")
    fun getComposers(): ResponseEntity<ComposerResponse> {
        return buildResponseEntity(ComposerResponse(composerService.findAll()))
    }

    @PostMapping(
        value = ["$apiV1Root/composers"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun saveComposer(@RequestBody composer: String): DatabaseWriteResponse {
        return composerService.save(toComposerDTO(composer))
    }
}
