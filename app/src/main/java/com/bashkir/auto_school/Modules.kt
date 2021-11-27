package com.bashkir.auto_school

import com.bashkir.auto_school.data.api.AutoSchoolApi
import com.bashkir.auto_school.data.repositories.AutoSchoolRepository
import com.bashkir.auto_school.data.services.AuthService
import com.bashkir.auto_school.data.services.StudentsService
import com.bashkir.auto_school.viewmodels.AuthState
import com.bashkir.auto_school.viewmodels.AuthViewModel
import com.bashkir.auto_school.viewmodels.StudentsState
import com.bashkir.auto_school.viewmodels.StudentsViewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val autoSchoolModule = module {
    factory { (state: StudentsState) -> StudentsViewModel(state, get()) }

    factory { (state: AuthState) -> AuthViewModel(state, get()) }

    single {
        Retrofit.Builder().baseUrl(AutoSchoolApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AutoSchoolApi::class.java)
    }

    single<StudentsService> {
        AutoSchoolRepository()
    } bind AuthService::class
}