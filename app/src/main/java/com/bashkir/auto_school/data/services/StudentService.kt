package com.bashkir.auto_school.data.services

import com.bashkir.auto_school.data.models.Lesson
import com.bashkir.auto_school.data.models.Teacher

interface StudentService {
    suspend fun getLessons(): List<Lesson>

    suspend fun getTeachers(): List<Teacher>

    suspend fun getAvailableTeacherLessons(teacher: Teacher) : List<Lesson>

    suspend fun signUpToLesson(lesson: Lesson)
}