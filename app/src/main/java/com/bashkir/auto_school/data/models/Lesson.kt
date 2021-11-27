package com.bashkir.auto_school.data.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Lesson(
    val date: LocalDateTime,
    val type: LessonType,
    @SerializedName("phoneNumber") val teacherPhone: String
)
