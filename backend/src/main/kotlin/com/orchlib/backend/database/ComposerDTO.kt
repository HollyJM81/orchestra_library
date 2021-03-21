package com.orchlib.backend.database
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.date

object ComposerDTO : Table() {
    val id = integer("id").autoIncrement()
    val last_name = varchar("last_name", 50)
    val middle_name = varchar("middle_name", 50)
    val first_name = varchar("first_name", 50)
    val date_of_birth = date("date_of_birth")
}
