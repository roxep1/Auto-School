package com.bashkir.auto_school.data.repositories

import com.bashkir.auto_school.data.api.AutoSchoolApi
import com.bashkir.auto_school.data.services.AuthService
import com.bashkir.auto_school.data.services.StudentsService
import com.bashkir.auto_school.data.models.Lesson
import com.bashkir.auto_school.data.models.Teacher
import org.koin.java.KoinJavaComponent.inject

class AutoSchoolRepository : StudentsService, AuthService {
    private val retrofit: AutoSchoolApi by inject(AutoSchoolApi::class.java)

    override suspend fun getLessons(): List<Lesson> = retrofit.getLessons()

    override suspend fun getTeachers(): List<Teacher> {
        TODO("Not yet implemented")
    }

    override suspend fun getAvailableTeacherLessons(id: String?): List<Lesson> {
        TODO("Not yet implemented")
    }

    override suspend fun clearHistory() = retrofit.clearHistory()

    override suspend fun login(login: String, password: String): Boolean {
        val response = retrofit.login(login, password)
        AutoSchoolApi.currentToken = response.token
        return response.isSuccess
    }
}