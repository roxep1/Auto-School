package com.bashkir.auto_school.viewmodels

import com.airbnb.mvrx.*
import com.bashkir.auto_school.data.models.Student
import com.bashkir.auto_school.data.models.Tariff
import com.bashkir.auto_school.data.models.Teacher
import com.bashkir.auto_school.data.models.User
import com.bashkir.auto_school.data.services.AdminService
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class AdminViewModel(
    initialState: AdminState,
    private val service: AdminService
) : MavericksViewModel<AdminState>(initialState) {

    init {
        getUsers()
        getTariffs()
        getPositions()
    }

    private fun getUsers() = suspend {
        service.getUsers()
    }.execute { copy(users = it) }

    fun createEmployee(user: User, employee: Teacher) = suspend {
        service.createEmployee(user, employee)
    }.execute {
        getUsers()
        copy()
    }

    fun createStudent(user: User, student: Student) = suspend {
        service.createStudent(user, student)
    }.execute {
        getUsers()
        copy()
    }

    private fun getTariffs() = suspend {
        service.getTariffs()
    }.execute { copy(tariffs = it) }

    private fun getPositions() = suspend {
        service.getPositions()
    }.execute { copy(positions = it) }

    companion object : MavericksViewModelFactory<AdminViewModel, AdminState>, KoinComponent {
        override fun create(
            viewModelContext: ViewModelContext,
            state: AdminState
        ): AdminViewModel = get { parametersOf(state) }
    }
}

data class AdminState(
    val users: Async<List<User>> = Uninitialized,
    val tariffs: Async<List<Tariff>> = Uninitialized,
    val positions: Async<List<String>> = Uninitialized,
//    val roles : List<Role> = Role.values().toList()
) : MavericksState