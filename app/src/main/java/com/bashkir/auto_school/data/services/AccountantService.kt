package com.bashkir.auto_school.data.services

import com.bashkir.auto_school.data.models.Teacher

interface AccountantService {
    suspend fun changeSalary(empId: String, salary : Float)

    suspend fun getEmployees(): List<Teacher>
}