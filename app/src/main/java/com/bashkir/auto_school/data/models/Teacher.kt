package com.bashkir.auto_school.data.models

import com.google.gson.annotations.SerializedName

data class Teacher(val positionName : String, @SerializedName("peopleInfo") val userInfo: User)
