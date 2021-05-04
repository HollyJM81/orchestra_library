package com.orchlib.backend.composer.responses

import com.orchlib.backend.composer.ComposerDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface ComposerGetResponse
data class ComposerGetSuccessResponse(val results: List<ComposerDTO>) : ComposerGetResponse

fun buildComposerGetSuccessResponseEntity(
    composerResponse: ComposerGetSuccessResponse
): ResponseEntity<ComposerGetResponse> {
    return ResponseEntity(composerResponse, HttpStatus.OK)
}
