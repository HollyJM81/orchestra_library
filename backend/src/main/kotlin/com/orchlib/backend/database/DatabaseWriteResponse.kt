package com.orchlib.backend.database

interface DatabaseWriteResponse

data class SaveSuccess(val message: String) : DatabaseWriteResponse

data class SaveFailure(val message: String) : DatabaseWriteResponse

fun buildAddSuccess(numberOfRowsAffected: Int) = SaveSuccess("Inserted $numberOfRowsAffected into composer table.")

fun buildAddFailure(data: String, table: String, exceptionMessage: String?): SaveFailure {
    return if (exceptionMessage == null) {
        SaveFailure("Error inserting $data into $table table.")
    } else {
        SaveFailure("Error inserting $data into $table table. $exceptionMessage")
    }
}
