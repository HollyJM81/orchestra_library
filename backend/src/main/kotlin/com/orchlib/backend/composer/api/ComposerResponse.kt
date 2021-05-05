package com.orchlib.backend.composer.api

import com.orchlib.backend.composer.ComposerDTO
import com.orchlib.backend.composer.persistence.DatabaseWriteResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface ComposerResponse

fun buildComposerResponseEntity(
    composerResponse: ComposerResponse,
    status: HttpStatus
): ResponseEntity<ComposerResponse> {
    return ResponseEntity(composerResponse, status)
}

data class ComposerGetSuccessResponse(val results: List<ComposerDTO>) : ComposerResponse

data class ComposerPostSuccessResponse(val writeResponse: DatabaseWriteResponse) : ComposerResponse
data class ComposerPostFailureResponse(val error: String?) : ComposerResponse
