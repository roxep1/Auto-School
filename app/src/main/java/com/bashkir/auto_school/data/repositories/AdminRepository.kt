package com.bashkir.auto_school.data.repositories

import com.bashkir.auto_school.data.api.AutoSchoolApi
import com.bashkir.auto_school.data.models.Student
import com.bashkir.auto_school.data.models.Tariff
import com.bashkir.auto_school.data.models.Teacher
import com.bashkir.auto_school.data.models.User
import com.bashkir.auto_school.data.services.AdminService
import org.koin.java.KoinJavaComponent

class AdminRepository : AdminService {
    private val retrofit: AutoSchoolApi by KoinJavaComponent.inject(AutoSchoolApi::class.java)

    override suspend fun getUsers(): List<User> = retrofit.getUsers()

    override suspend fun createEmployee(user: User, employee: Teacher) =
        retrofit.createUser(user, employee)

    override suspend fun createStudent(user: User, student: Student) =
        retrofit.createUser(user, student)

    override suspend fun getPositions(): List<String> =
        retrofit.getPositions()

    override suspend fun getTariffs(): List<Tariff> =
        retrofit.getTariffs()
}