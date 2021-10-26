package com.bashkir.auto_school.viewmodels

import com.airbnb.mvrx.*
import com.bashkir.auto_school.data.StudentsRepository
import com.bashkir.auto_school.models.Lesson
import com.bashkir.auto_school.models.User
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject

class StudentsViewModel(
    initialState: StudentsState,
    private val repository: StudentsRepository
) :
    MavericksViewModel<StudentsState>(initialState) {
    private var currentUser: User? = null

    init {
        onAsync(StudentsState::user) {
            currentUser = it
        }
    }

//    fun getLessons(): List<Lesson> =
//        repository.getLessons(currentUser?.phoneNumber)


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

data class StudentsState(val lessons: Async<List<Lesson>>, val user: Async<User>) : MavericksState