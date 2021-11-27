package com.bashkir.auto_school.viewmodels


import com.airbnb.mvrx.*
import com.bashkir.auto_school.data.services.AuthService
import com.bashkir.auto_school.activities.StudentActivity
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject

class AuthViewModel(
    initialState: AuthState,
    private val service: AuthService
) :
    MavericksViewModel<AuthState>(initialState) {
    fun login(login: String, password: String, navigate: (Class<*>) -> Unit) {
        onAsync(AuthState::isAuthorized) {
            if (it)
                navigate(StudentActivity::class.java)
        }
        suspend { service.login(login, password) }.execute { copy(isAuthorized = it) }
    }

    companion object : MavericksViewModelFactory<AuthViewModel, AuthState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: AuthState
        ): AuthViewModel {
            val vm: AuthViewModel by inject(AuthViewModel::class.java) {
                parametersOf(state)
            }
            return vm
        }
    }
}

data class AuthState(val isAuthorized: Async<Boolean> = Uninitialized) : MavericksState
