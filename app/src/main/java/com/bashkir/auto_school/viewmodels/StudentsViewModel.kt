package com.bashkir.auto_school.viewmodels

import com.airbnb.mvrx.*
import com.bashkir.auto_school.data.models.Lesson
import com.bashkir.auto_school.data.models.Teacher
import com.bashkir.auto_school.data.models.User
import com.bashkir.auto_school.data.services.StudentsService
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class StudentsViewModel(
    initialState: StudentsState,
    private val service: StudentsService
) : MavericksViewModel<StudentsState>(initialState) {

    init {
        getLessons()
        getTeachers()
    }

    private fun getLessons() = suspend {
        service.getLessons()
    }.execute { copy(lessons = it) }

    fun clearHistory() = suspend {
        service.clearHistory()
    }.execute {
        getLessons()
        copy()
    }

    private fun getTeachers() = suspend {
        service.getTeachers()
    }.execute { copy(teachers = it) }

    fun getTeacherLessons(teacher: Teacher) = suspend {
        service.getAvailableTeacherLessons(teacher)
    }.execute { copy(teacherLessons = it) }

    fun signUpToLesson(lesson: Lesson, currentTeacherId: String) = suspend {
        service.signUpToLesson(lesson)
    }.execute {
        getTeacherLessons(teachers()!!.find { it.userInfo.phoneNumber == currentTeacherId }!!)
        getLessons()
        copy()
    }

    companion object : MavericksViewModelFactory<StudentsViewModel, StudentsState>, KoinComponent {
        override fun create(
            viewModelContext: ViewModelContext,
            state: StudentsState
        ): StudentsViewModel = get { parametersOf(state) }
    }
}

data class StudentsState(
    val lessons: Async<List<Lesson>> = Uninitialized,
    val user: Async<User> = Uninitialized,
    val teachers: Async<List<Teacher>> = Uninitialized,
    val teacherLessons: Async<List<Lesson>> = Uninitialized
) : MavericksState

