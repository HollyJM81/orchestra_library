package com.orchlib.backend.database

interface DatabaseResponse

data class AddSuccess(val message: String) : DatabaseResponse

data class AddFailure(val message: String) : DatabaseResponse

fun buildAddSuccess(numberOfRowsAffected: Int) = AddSuccess("Inserted $numberOfRowsAffected into composer table.")

fun buildAddFailure(composerLastName: String) = AddFailure("Error inserting $composerLastName into composer table.")
