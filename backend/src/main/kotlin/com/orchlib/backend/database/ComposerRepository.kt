package com.orchlib.backend.database

interface ComposerRepository {
    fun findAll(): ComposerDTO?
    fun findOne(id: Int): ComposerDTO?
    fun save(composerDTO: ComposerDTO): DatabaseWriteResponse
}
