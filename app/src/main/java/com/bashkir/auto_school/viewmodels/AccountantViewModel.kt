package com.bashkir.auto_school.viewmodels

import com.airbnb.mvrx.*
import com.bashkir.auto_school.data.models.Teacher
import com.bashkir.auto_school.data.services.AccountantService
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class AccountantViewModel(
    initialState: AccountantState,
    private val service: AccountantService
) : MavericksViewModel<AccountantState>(initialState) {
    init {
        getEmployees()
    }

    private fun getEmployees() = suspend {
        service.getEmployees()
    }.execute { copy(employees = it) }

    fun changeSalary(phoneNumber: String, salary: Float) = suspend {
        service.changeSalary(phoneNumber, salary)
    }.execute {
        getEmployees()
        copy()
    }

    companion object : MavericksViewModelFactory<AccountantViewModel, AccountantState>,
        KoinComponent {
        override fun create(
            viewModelContext: ViewModelContext,
            state: AccountantState
        ): AccountantViewModel = get { parametersOf(state) }
    }
}

data class AccountantState(
    val employees: Async<List<Teacher>> = Uninitialized
) : MavericksState