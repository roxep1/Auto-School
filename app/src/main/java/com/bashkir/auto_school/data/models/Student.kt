package com.bashkir.auto_school.data.models

import java.time.LocalDate
import java.time.LocalDateTime

data class Student(val userInfo : User, val graduation: LocalDate, val tariff: Tariff)
