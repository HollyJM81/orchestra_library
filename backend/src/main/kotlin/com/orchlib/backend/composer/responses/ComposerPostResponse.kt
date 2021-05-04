package com.orchlib.backend.composer.responses

import com.orchlib.backend.composer.persistence.DatabaseWriteResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface ComposerPostResponse
data class ComposerPostSuccessResponse(val writeResponse: DatabaseWriteResponse) : ComposerPostResponse
data class ComposerPostFailureResponse(val error: String?) : ComposerPostResponse

fun buildComposerPostResponseEntity(
    composerResponse: ComposerPostResponse,
    status: HttpStatus
): ResponseEntity<ComposerPostResponse> {
    return ResponseEntity(composerResponse, status)
}
