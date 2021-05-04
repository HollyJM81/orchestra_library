package com.orchlib.backend.database

import org.springframework.jdbc.core.RowMapper
import java.sql.Date
import java.sql.ResultSet
import java.sql.SQLException

class ComposerRowMapper : RowMapper<ComposerDTO> {
    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rowNum: Int): ComposerDTO {
        return ComposerDTO(
            last_name = rs.getString("last_name"),
            middle_name = rs.getString("middle_name"),
            first_name = rs.getString("first_name"),
            date_of_birth = Date.valueOf(rs.getString("date_of_birth"))
        )
    }
}
