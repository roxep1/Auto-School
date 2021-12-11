package com.bashkir.auto_school.viewmodels

import com.airbnb.mvrx.*
import com.bashkir.auto_school.data.models.Teacher
import com.bashkir.auto_school.data.models.Vacation
import com.bashkir.auto_school.data.services.HumanResService
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class HumanResViewModel(
    initialState: HumanResState,
    private val service: HumanResService
) : MavericksViewModel<HumanResState>(initialState) {

    init {
        getEmployees()
        getVacations()
    }

    private fun getEmployees() = suspend {
        service.getEmployees()
    }.execute { copy(employees = it) }

    private fun getVacations() = suspend {
        service.getVacations()
    }.execute { copy(vacations = it) }

    fun createVacation(coef: Float, periodOfVacation: String, phoneNumber: String) = suspend {
        service.createVacation(Vacation(coef, periodOfVacation, phoneNumber))
    }.execute {
        getVacations()
        copy()
    }

    companion object : MavericksViewModelFactory<HumanResViewModel, HumanResState>, KoinComponent {
        override fun create(
            viewModelContext: ViewModelContext,
            state: HumanResState
        ): HumanResViewModel = get { parametersOf(state) }
    }
}

data class HumanResState(
    val employees: Async<List<Teacher>> = Uninitialized,
    val vacations: Async<List<Vacation>> = Uninitialized
) : MavericksState