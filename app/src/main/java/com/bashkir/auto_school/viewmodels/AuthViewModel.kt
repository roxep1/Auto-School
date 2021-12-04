package com.bashkir.auto_school.viewmodels


import androidx.compose.material.ExperimentalMaterialApi
import com.airbnb.mvrx.*
import com.bashkir.auto_school.data.services.AuthService
import com.bashkir.auto_school.activities.StudentActivity
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject

@ExperimentalMaterialApi
class AuthViewModel(
    initialState: AuthState,
    private val service: AuthService
) :
    MavericksViewModel<AuthState>(initialState) {

    fun login(login: String, password: String, navigate: (Class<*>) -> Unit) {
        onAsync(AuthState::token) {
                navigate(StudentActivity::class.java)
        }
        suspend { service.login(login, password) }.execute { copy(token = it) }
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

data class AuthState(val token: Async<String> = Uninitialized) : MavericksState
