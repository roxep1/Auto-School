package com.bashkir.auto_school.data.repositories

import com.bashkir.auto_school.data.api.AuthApi
import com.bashkir.auto_school.data.services.AuthService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject


class AuthRepository : AuthService {
    private val retrofit: AuthApi by inject(AuthApi::class.java)

    override suspend fun login(login: String, password: String): String =
        retrofit.login(login, password).token?:""
}