package com.bashkir.auto_school.viewmodels


import androidx.compose.material.ExperimentalMaterialApi
import com.airbnb.mvrx.*
import com.bashkir.auto_school.activities.AccountantActivity
import com.bashkir.auto_school.activities.AdminActivity
import com.bashkir.auto_school.activities.StudentActivity
import com.bashkir.auto_school.activities.TeacherActivity
import com.bashkir.auto_school.data.models.LoginResponse
import com.bashkir.auto_school.data.models.Role
import com.bashkir.auto_school.data.services.AuthService
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

@ExperimentalMaterialApi
class AuthViewModel(
    initialState: AuthState,
    private val service: AuthService
) :
    MavericksViewModel<AuthState>(initialState) {

    fun login(login: String, password: String, navigate: (Class<*>) -> Unit) =
        suspend { service.login(login, password) }.execute {
            if (it is Success)
                when (it().role) {
                    Role.STUDENT -> navigate(StudentActivity::class.java)
                    Role.ACCOUNTANT -> navigate(AccountantActivity::class.java)
                    Role.TEACHER -> navigate(TeacherActivity::class.java)
                    Role.ADMIN -> navigate(AdminActivity::class.java)
                }
            copy(loginResponse = it)
        }


    companion object : MavericksViewModelFactory<AuthViewModel, AuthState>, KoinComponent {
        override fun create(
            viewModelContext: ViewModelContext,
            state: AuthState
        ): AuthViewModel = get { parametersOf(state) }
    }
}

data class AuthState(val loginResponse: Async<LoginResponse> = Uninitialized) : MavericksState
