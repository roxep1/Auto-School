package com.bashkir.auto_school.data.models

import com.google.gson.annotations.SerializedName

data class LessonType(val id : Int, val name: String, @SerializedName("onePlace") val isDriving: Boolean, val duration: Int){
    override fun toString(): String = name
}
