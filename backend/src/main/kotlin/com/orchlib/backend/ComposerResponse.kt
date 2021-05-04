package com.orchlib.backend

import com.orchlib.backend.database.ComposerDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

data class ComposerResponse(val results: List<ComposerDTO>)

fun buildResponseEntity(composerResponse: ComposerResponse): ResponseEntity<ComposerResponse> {
    return ResponseEntity(composerResponse, HttpStatus.OK)
}