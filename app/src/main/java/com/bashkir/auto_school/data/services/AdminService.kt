package com.bashkir.auto_school.data.services

import com.bashkir.auto_school.data.models.Student
import com.bashkir.auto_school.data.models.Tariff
import com.bashkir.auto_school.data.models.Teacher
import com.bashkir.auto_school.data.models.User

interface AdminService {
    suspend fun getUsers() : List<User>

    suspend fun createEmployee(user: User, employee: Teacher)

    suspend fun createStudent(user: User, student: Student)

    suspend fun getPositions() : List<String>

    suspend fun getTariffs(): List<Tariff>
}