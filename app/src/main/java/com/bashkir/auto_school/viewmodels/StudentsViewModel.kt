package com.bashkir.auto_school.viewmodels

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.bashkir.auto_school.data.interfaces.StudentsRepository
import com.bashkir.auto_school.states.StudentsState
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject

class StudentsViewModel(
    initialState: StudentsState,
    private val repository: StudentsRepository
) :
    MavericksViewModel<StudentsState>(initialState) {


    fun getLessons() = suspend {
        repository.getLessons()
    }.execute { copy(lessons = it) }

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

