package com.bashkir.auto_school.viewmodels


import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.bashkir.auto_school.data.interfaces.AuthRepository
import com.bashkir.auto_school.states.AuthState
import com.bashkir.auto_school.student.StudentActivity
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject
import kotlin.reflect.KClass

class AuthViewModel(
    initialState: AuthState,
    private val repository: AuthRepository
) :
    MavericksViewModel<AuthState>(initialState) {
    fun login(login: String, password: String, navigate: (Class<*>) -> Unit) {
        onAsync(AuthState::isAuthorized) {
            if(it)
                navigate(StudentActivity::class.java)
        }
        suspend { repository.login(login, password) }.execute { copy(isAuthorized = it) }
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