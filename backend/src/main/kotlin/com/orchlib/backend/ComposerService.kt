package com.orchlib.backend

import com.orchlib.backend.database.ComposerDTO
import com.orchlib.backend.database.DatabaseWriteResponse
import com.orchlib.backend.database.JdbcComposerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ComposerService {
    @Autowired
    private lateinit var jdbcComposerRepository: JdbcComposerRepository

    fun findAll(): List<ComposerDTO> {
        return jdbcComposerRepository.findAll()
    }

    fun findOne(id: Int): ComposerDTO? {
        return jdbcComposerRepository.findById(id)
    }

    fun save(composer: ComposerDTO): DatabaseWriteResponse {
        return jdbcComposerRepository.save(composer)
    }
}
