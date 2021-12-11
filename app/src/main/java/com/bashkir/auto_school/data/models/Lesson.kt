package com.bashkir.auto_school.data.models

import com.bashkir.auto_school.Utils.fromInstantToLocalDateTime
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Lesson(
    val id: Int? = null,

    @SerializedName("date")
    val dateString: String,
    val type: LessonType,

    @SerializedName("phoneNumber")
    val teacherPhone: String? = null,

    val students: List<Student>? = null
) {
    val date: LocalDateTime
        get() = dateString.fromInstantToLocalDateTime()
}
