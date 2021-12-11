package com.bashkir.auto_school.data.services

import com.bashkir.auto_school.data.models.LoginResponse

interface AuthService {
    suspend fun login(login : String, password: String): LoginResponse
}