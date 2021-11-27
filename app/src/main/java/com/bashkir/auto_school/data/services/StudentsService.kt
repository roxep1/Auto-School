package com.bashkir.auto_school.data.services

import com.bashkir.auto_school.data.models.Lesson
import com.bashkir.auto_school.data.models.Teacher

interface StudentsService {
    suspend fun getLessons(): List<Lesson>

    suspend fun getTeachers(): List<Teacher>

    suspend fun getAvailableTeacherLessons(id: String?) : List<Lesson>

    suspend fun clearHistory()
}

