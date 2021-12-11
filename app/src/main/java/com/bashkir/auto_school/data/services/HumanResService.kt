package com.bashkir.auto_school.data.services

import com.bashkir.auto_school.data.models.Teacher
import com.bashkir.auto_school.data.models.Vacation

interface HumanResService {
    suspend fun createVacation(vacation : Vacation)

    suspend fun getEmployees() : List<Teacher>

    suspend fun getVacations() : List<Vacation>
}
