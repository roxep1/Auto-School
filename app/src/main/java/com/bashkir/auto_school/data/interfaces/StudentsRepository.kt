package com.bashkir.auto_school.data.interfaces

import com.bashkir.auto_school.models.Lesson
import com.bashkir.auto_school.models.Teacher
import com.bashkir.auto_school.models.User

interface StudentsRepository {
    suspend fun getLessons(): List<Lesson>

    suspend fun getTeachers(): List<Teacher>

    suspend fun getAvailableTeacherLessons(id: String?) : List<Lesson>
}

