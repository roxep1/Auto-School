package com.bashkir.auto_school

import androidx.compose.material.ExperimentalMaterialApi
import com.bashkir.auto_school.data.api.AutoSchoolApi
import com.bashkir.auto_school.data.repositories.*
import com.bashkir.auto_school.data.services.*
import com.bashkir.auto_school.viewmodels.*
import okhttp3.OkHttpClient
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    single(named("BASE_URL")) { "https://auto-school.herokuapp.com/" }

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

val servicesModule = module {
    single<StudentService> { StudentRepository() }

    single<AuthService> { AuthRepository() }

    single<AccountantService> { AccountantRepository() }

    single<AdminService> { AdminRepository() }

    single<HumanResService> { HumanResRepository() }

    single<TeacherService> { TeacherRepository() }
}

@ExperimentalMaterialApi
val viewModelsModule = module {
    factory { params -> StudentsViewModel(params.get(), get()) }

    factory { params -> AuthViewModel(params.get(), get()) }

    factory { params -> AccountantViewModel(params.get(), get()) }

    factory { params -> AdminViewModel(params.get(), get()) }

    factory { params -> HumanResViewModel(params.get(), get()) }

    factory { params -> TeachersViewModel(params.get(), get()) }
}

fun loadToken(token: String) = loadKoinModules(module {
    single(named("token")) { token }
})