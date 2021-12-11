package com.bashkir.auto_school.viewmodels

import com.airbnb.mvrx.*
import com.bashkir.auto_school.data.models.Lesson
import com.bashkir.auto_school.data.models.LessonType
import com.bashkir.auto_school.data.services.TeacherService
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class TeachersViewModel(
    initialState: TeachersState,
    private val service: TeacherService
) : MavericksViewModel<TeachersState>(initialState) {

    init {
        getLessons()
        getLessonTypes()
    }

    private fun getLessons() = suspend {
        service.getLessons()
    }.execute { copy(lessons = it) }

    fun createLesson(lesson: Lesson) = suspend {
        service.createLesson(lesson)
    }.execute {
        getLessons()
        copy()
    }

    private fun getLessonTypes() = suspend{
        service.getLessonTypes()
    }.execute { copy(lessonTypes = it) }

    companion object : MavericksViewModelFactory<TeachersViewModel, TeachersState>, KoinComponent {
        override fun create(
            viewModelContext: ViewModelContext,
            state: TeachersState
        ): TeachersViewModel = get { parametersOf(state) }
    }
}

data class TeachersState(
    val lessons: Async<List<Lesson>> = Uninitialized,
    val lessonTypes: Async<List<LessonType>> = Uninitialized
) : MavericksState