package com.bashkir.auto_school.data.repositories

import com.bashkir.auto_school.data.api.AutoSchoolApi
import com.bashkir.auto_school.data.models.Teacher
import com.bashkir.auto_school.data.models.Vacation
import com.bashkir.auto_school.data.services.HumanResService
import org.koin.java.KoinJavaComponent

class HumanResRepository: HumanResService {
    private val retrofit: AutoSchoolApi by KoinJavaComponent.inject(AutoSchoolApi::class.java)

    override suspend fun createVacation(vacation: Vacation) = retrofit.createVacation(vacation)

    override suspend fun getEmployees(): List<Teacher> = retrofit.getEmployees()

    override suspend fun getVacations(): List<Vacation> = retrofit.getVacations()
}