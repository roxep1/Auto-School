package com.bashkir.auto_school.states

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.bashkir.auto_school.models.Lesson
import com.bashkir.auto_school.models.User

data class StudentsState(val lessons: Async<List<Lesson>> = Uninitialized, val user: Async<User> = Uninitialized) : MavericksState