package com.bashkir.auto_school

import androidx.compose.material.ExperimentalMaterialApi
import com.bashkir.auto_school.data.api.AutoSchoolApi
import com.bashkir.auto_school.data.repositories.AuthRepository
import com.bashkir.auto_school.data.repositories.AutoSchoolRepository
import com.bashkir.auto_school.data.services.AuthService
import com.bashkir.auto_school.data.services.StudentsService
import com.bashkir.auto_school.viewmodels.AuthState
import com.bashkir.auto_school.viewmodels.AuthViewModel
import com.bashkir.auto_school.viewmodels.StudentsState
import com.bashkir.auto_school.viewmodels.StudentsViewModel
import okhttp3.OkHttpClient
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalMaterialApi
val autoSchoolModule = module {
    factory { (state: StudentsState) -> StudentsViewModel(state, get()) }

    factory { (state: AuthState) -> AuthViewModel(state, get()) }

    single(named("BASE_URL")) { "https://auto-school.herokuapp.com/" }

    single<StudentsService> {
        AutoSchoolRepository()
    }

    single<AuthService> {
        AuthRepository()
    }

    factory {
        val token = getOrNull<String>(named("token"))
        OkHttpClient.Builder().addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            if (token != null)
                requestBuilder.addHeader("Authorization", "Bearer ${get<String>(named("token"))}")
            val request = requestBuilder.build()

            chain.proceed(request)
        }.build()
    }

    factory {
        Retrofit.Builder().baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(AutoSchoolApi::class.java)
    }
}

fun loadToken(token: String) = loadKoinModules(module {
    single(named("token")) { token }
})