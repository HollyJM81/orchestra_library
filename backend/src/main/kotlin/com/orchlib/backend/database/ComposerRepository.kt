package com.orchlib.backend.database

interface ComposerRepository {
    fun findAll(): ComposerDTO
    fun findOne(): ComposerDTO
    fun save(composerDTO: ComposerDTO): DatabaseWriteResponse
}