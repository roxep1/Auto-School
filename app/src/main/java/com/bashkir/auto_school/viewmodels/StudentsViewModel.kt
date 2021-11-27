package com.bashkir.auto_school.viewmodels

import com.airbnb.mvrx.*
import com.bashkir.auto_school.data.models.Lesson
import com.bashkir.auto_school.data.models.User
import com.bashkir.auto_school.data.services.StudentsService
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject

class StudentsViewModel(
    initialState: StudentsState,
    private val service: StudentsService
) : MavericksViewModel<StudentsState>(initialState) {

    init {
        getLessons()
    }

    fun getLessons() = suspend {
        service.getLessons()
    }.execute { copy(lessons = it) }

    fun clearHistory() = suspend {
        service.clearHistory()
    }.execute {
        getLessons()
        copy()
    }

    companion object : MavericksViewModelFactory<StudentsViewModel, StudentsState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: StudentsState
        ): StudentsViewModel {
            val vm: StudentsViewModel by inject(StudentsViewModel::class.java) {
                parametersOf(state)
            }
            return vm
        }
    }
}

data class StudentsState(
    val lessons: Async<List<Lesson>> = Uninitialized,
    val user: Async<User> = Uninitialized
) : MavericksState

