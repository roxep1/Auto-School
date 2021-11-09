package com.bashkir.auto_school.data.interfaces

import com.bashkir.auto_school.models.User

interface AuthRepository {
    suspend fun login(login : String, password: String): Boolean
}