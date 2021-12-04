package com.bashkir.auto_school.data.services

interface AuthService {
    suspend fun login(login : String, password: String): String
}