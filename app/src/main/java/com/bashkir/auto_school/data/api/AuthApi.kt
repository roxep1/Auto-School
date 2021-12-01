package com.bashkir.auto_school.data.api

import com.bashkir.auto_school.data.models.LoginResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST("login")
    suspend fun login(
        @Query("login") login: String,
        @Query("password") password: String
    ): LoginResponse
}