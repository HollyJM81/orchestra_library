package com.orchlib.backend.database

interface DatabaseResponse

data class AddSuccess(val id: Int) : DatabaseResponse

data class AddFailure(val message: String = "Error inserting into database.") : DatabaseResponse
