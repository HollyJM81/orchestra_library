package com.orchlib.backend.composer.persistence

interface DatabaseWriteResponse

data class SaveSuccess(val message: String) : DatabaseWriteResponse

data class SaveFailure(val message: String) : DatabaseWriteResponse

fun buildAddSuccess(
    data: String,
    table: String,
    numberOfRowsAffected: Int
): SaveSuccess {
    return SaveSuccess("Inserted $data into $table table. Rows affected: $numberOfRowsAffected.")
}

fun buildAddFailure(data: String, table: String, exceptionMessage: String?): SaveFailure {
    return if (exceptionMessage == null) {
        SaveFailure("Error inserting $data into $table table.")
    } else {
        SaveFailure("Error inserting $data into $table table. $exceptionMessage")
    }
}
