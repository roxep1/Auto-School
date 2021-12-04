package com.bashkir.auto_school.data.repositories

import com.bashkir.auto_school.data.api.AutoSchoolApi
import com.bashkir.auto_school.data.services.AuthService
import com.bashkir.auto_school.loadToken
import org.koin.java.KoinJavaComponent.inject

class AuthRepository: AuthService {
    private val retrofit : AutoSchoolApi by inject(AutoSchoolApi::class.java)

    override suspend fun login(login: String, password: String): String {
        val token = retrofit.login(login, password).token
        loadToken(token)
        return token
    }
}