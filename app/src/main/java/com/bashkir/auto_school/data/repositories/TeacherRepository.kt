package com.bashkir.auto_school.data.repositories

import com.bashkir.auto_school.data.api.AutoSchoolApi
import com.bashkir.auto_school.data.models.Lesson
import com.bashkir.auto_school.data.models.LessonType
import com.bashkir.auto_school.data.services.TeacherService
import org.koin.java.KoinJavaComponent

class TeacherRepository : TeacherService{
    private val retrofit: AutoSchoolApi by KoinJavaComponent.inject(AutoSchoolApi::class.java)

    override suspend fun getLessons(): List<Lesson> = retrofit.getTeacherLessons()

    override suspend fun createLesson(lesson: Lesson) = retrofit.createLesson(lesson)

    override suspend fun getLessonTypes(): List<LessonType> = retrofit.getLessonTypes()
}