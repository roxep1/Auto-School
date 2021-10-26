package com.bashkir.auto_school.data

import com.bashkir.auto_school.models.Lesson
import com.bashkir.auto_school.models.Teacher

interface StudentsRepository {
    suspend fun getLessons(id: String?): List<Lesson>

    suspend fun getTeachers(): List<Teacher>

    suspend fun getAvailableTeacherLessons(id: String?) : List<Lesson>
}