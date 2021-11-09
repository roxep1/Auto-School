package com.bashkir.auto_school.data

import com.bashkir.auto_school.data.interfaces.AuthRepository
import com.bashkir.auto_school.data.interfaces.StudentsRepository
import com.bashkir.auto_school.models.Lesson
import com.bashkir.auto_school.models.Teacher
import org.koin.java.KoinJavaComponent.inject

class AutoSchoolRepository : StudentsRepository, AuthRepository {
    private val retrofit: AutoSchoolApi by inject(AutoSchoolApi::class.java)

    override suspend fun getLessons(): List<Lesson> = retrofit.getLessons()

    override suspend fun getTeachers(): List<Teacher> {
        TODO("Not yet implemented")
    }

    override suspend fun getAvailableTeacherLessons(id: String?): List<Lesson> {
        TODO("Not yet implemented")
    }

    override suspend fun login(login: String, password: String): Boolean {
        val response = retrofit.login(login, password)
        AutoSchoolApi.currentToken = response.token
        return response.isSuccess
    }
}