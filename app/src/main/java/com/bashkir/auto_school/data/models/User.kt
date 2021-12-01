package com.bashkir.auto_school.data.models

data class User(
    val phoneNumber: String,
    val name: String,
    val lastName: String,
    val middleName: String?,
    val email: String?
) {
    override fun toString(): String = "$name $middleName ${lastName.first()}."
}
