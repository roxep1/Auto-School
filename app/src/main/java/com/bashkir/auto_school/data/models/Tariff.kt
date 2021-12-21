package com.bashkir.auto_school.data.models

data class Tariff(val cost: Float, val name : String, val id: Int? = null){
    override fun toString(): String = name
}
