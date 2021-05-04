package com.orchlib.backend.database

interface ComposerRepository {
    fun findAll(): List<ComposerDTO>
    fun findById(id: Int): ComposerDTO?
    fun save(composerDTO: ComposerDTO): DatabaseWriteResponse
}
