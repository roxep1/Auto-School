package com.bashkir.auto_school.data

import com.bashkir.auto_school.models.Lesson
import com.bashkir.auto_school.models.LoginResponse
import retrofit2.http.*

interface AutoSchoolApi {

    companion object {
        const val BASE_URL = "https://auto-school.herokuapp.com/"
        var currentToken: String? = null
    }

    @POST("login")
    suspend fun login(@Query("login")login: String, @Query("password") password: String): LoginResponse

    @GET("student/lessons")
    suspend fun getLessons(@Header("Authorization") token: String = "Bearer $currentToken"): List<Lesson>
}