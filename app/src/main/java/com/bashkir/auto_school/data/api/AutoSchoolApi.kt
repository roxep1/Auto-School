package com.bashkir.auto_school.data.api

import com.bashkir.auto_school.data.models.*
import retrofit2.http.*

interface AutoSchoolApi {

    @GET("student/lessons")
    suspend fun getLessons(): List<Lesson>

    @GET("student/teachers")
    suspend fun getTeachers(): List<Teacher> //@Header("Authorization") token: String = "Bearer $currentToken"

    @GET("student/teachers/{id}/lessons")
    suspend fun getTeacherLessons(
        @Path("id") teacherPhone: String
    ): List<Lesson>

    @POST("student/signUp/{id}")
    suspend fun signUpToLesson(@Path("id") lessonId: Int)

    @GET("users")
    suspend fun getUsers(): List<User>

    @POST("admin/student")
    suspend fun createUser(@Body user: User, @Body student: Student)

    @POST("admin/employee")
    suspend fun createUser(@Body user: User, @Body teacher: Teacher)

    @GET("positions")
    suspend fun getPositions(): List<String>

    @GET("tariffs")
    suspend fun getTariffs(): List<Tariff>

    @PUT("accountant/salary/{id}")
    suspend fun changeSalary(@Body salary : Float, @Path("id") phoneNumber: String) 

    @GET("employees")
    suspend fun getEmployees() : List<Teacher>

    @POST("hr/vacation")
    suspend fun createVacation(@Body vacation: Vacation) //

    @GET("hr/vacation")
    suspend fun getVacations(): List<Vacation> //

    @GET("teacher/lesson")
    suspend fun getTeacherLessons(): List<Lesson>

    @POST("teacher/lesson")
    suspend fun createLesson(@Body lesson: Lesson)

    @GET("teacher/lesson/types")
    suspend fun getLessonTypes() : List<LessonType>

    @POST("login")
    suspend fun login(
        @Body cred: Cred
    ): LoginResponse
}