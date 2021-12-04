package com.bashkir.auto_school.data.api

import com.bashkir.auto_school.data.models.Lesson
import com.bashkir.auto_school.data.models.LoginResponse
import com.bashkir.auto_school.data.models.Teacher
import retrofit2.http.*
import java.time.LocalDateTime

interface AutoSchoolApi {

    @GET("student/lessons")
    suspend fun getLessons(): List<Lesson>

    @DELETE("student/lessons")
    suspend fun clearHistory(
        @Query("now") now: LocalDateTime = LocalDateTime.now()
    )

    @GET("student/teachers")
    suspend fun getTeachers(): List<Teacher> //@Header("Authorization") token: String = "Bearer $currentToken"

    @GET("student/teachers/{id}/lessons")
    suspend fun getTeacherLessons(
        @Path("id") teacherPhone: String
    ): List<Lesson>

    @POST("student/signUp/{id}")
    suspend fun signUpToLesson(@Path("id") lessonId: Int)

    @POST("login")
    suspend fun login(
        @Query("login") login: String,
        @Query("password") password: String
    ): LoginResponse
}