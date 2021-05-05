package com.orchlib.backend.composer.persistence

import com.orchlib.backend.composer.ComposerDTO

interface ComposerRepository {
    fun findAll(): List<ComposerDTO>
    fun findById(id: Int): ComposerDTO?
    fun save(composerDTO: ComposerDTO): DatabaseWriteResponse
}
