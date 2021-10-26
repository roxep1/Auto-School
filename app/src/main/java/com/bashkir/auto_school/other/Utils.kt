package com.bashkir.auto_school.other

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Month

object Utils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun translateMonth(month: Month) : String =
        when(month){
            Month.APRIL -> "Апрель"
            Month.AUGUST ->  "Август"
            Month.JANUARY -> "Январь"
            Month.FEBRUARY -> "Февраль"
            Month.MARCH -> "Март"
            Month.MAY -> "Май"
            Month.JUNE -> "Июнь"
            Month.JULY -> "Июль"
            Month.SEPTEMBER -> "Сентябрь"
            Month.OCTOBER -> "Октябрь"
            Month.NOVEMBER -> "Ноябрь"
            Month.DECEMBER -> "Декабрь"
        }

}