package com.bashkir.auto_school.states

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.bashkir.auto_school.models.User

data class AuthState(val isAuthorized: Async<Boolean> = Uninitialized): MavericksState
