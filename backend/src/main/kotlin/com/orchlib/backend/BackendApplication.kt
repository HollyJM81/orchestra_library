package com.orchlib.backend

import com.orchlib.backend.database.ComposerDTO
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {
    val dbUrl = "jdbc:postgresql://localhost:5432/paulamuldoon"
    val dbUser = "dev"
    val dbPass = "dev_database_password"
    Database.connect(dbUrl, driver = "org.postgresql.Driver", user = dbUser, password = dbPass)
    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.drop(ComposerDTO)
        SchemaUtils.create(ComposerDTO)
        ComposerDTO.insert { composerDTO ->
            composerDTO[last_name] = "Muldoon"
            composerDTO[middle_name] = "Therse"
            composerDTO[first_name] = "Paula"
            composerDTO[date_of_birth] = DateTime.parse("2020-10-10")
        }
    }
    runApplication<BackendApplication>(*args)
}
