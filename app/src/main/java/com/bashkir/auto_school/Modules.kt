package com.bashkir.auto_school

import com.bashkir.auto_school.viewmodels.StudentsState
import com.bashkir.auto_school.viewmodels.StudentsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val studentsModule = module {

    factory { (state: StudentsState) -> StudentsViewModel(state, get()) }
}