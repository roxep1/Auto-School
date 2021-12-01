package com.bashkir.auto_school.data.repositories

import com.bashkir.auto_school.data.api.AutoSchoolApi
import com.bashkir.auto_school.data.models.Lesson
import com.bashkir.auto_school.data.models.Teacher
import com.bashkir.auto_school.data.services.AuthService
import com.bashkir.auto_school.data.services.StudentsService
import org.koin.java.KoinJavaComponent.inject

class AutoSchoolRepository : StudentsService {
    private val retrofit: AutoSchoolApi by inject(AutoSchoolApi::class.java)

    override suspend fun getLessons(): List<Lesson> = retrofit.getLessons()

    override suspend fun getTeachers(): List<Teacher> = retrofit.getTeachers()

    override suspend fun getAvailableTeacherLessons(teacher: Teacher): List<Lesson> =
        retrofit.getTeacherLessons(teacher.userInfo.phoneNumber)

    override suspend fun clearHistory() = retrofit.clearHistory()

    override suspend fun signUpToLesson(lesson: Lesson) = retrofit.signUpToLesson(lesson.id)

}