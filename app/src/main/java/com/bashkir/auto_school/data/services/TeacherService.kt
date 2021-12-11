package com.bashkir.auto_school.data.services

import com.bashkir.auto_school.data.models.Lesson
import com.bashkir.auto_school.data.models.LessonType

interface TeacherService {
    suspend fun getLessons(): List<Lesson>

    suspend fun createLesson(lesson: Lesson)

    suspend fun getLessonTypes() : List<LessonType>
}