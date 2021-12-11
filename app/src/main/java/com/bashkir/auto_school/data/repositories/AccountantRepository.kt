package com.bashkir.auto_school.data.repositories

import com.bashkir.auto_school.data.api.AutoSchoolApi
import com.bashkir.auto_school.data.models.Teacher
import com.bashkir.auto_school.data.services.AccountantService
import org.koin.java.KoinJavaComponent

class AccountantRepository : AccountantService {
    private val retrofit: AutoSchoolApi by KoinJavaComponent.inject(AutoSchoolApi::class.java)

    override suspend fun changeSalary(empId: String, salary: Float) =
        retrofit.changeSalary(salary, empId)

    override suspend fun getEmployees(): List<Teacher> = retrofit.getEmployees()

}