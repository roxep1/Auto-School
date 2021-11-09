package com.bashkir.auto_school

import android.app.Application
import com.airbnb.mvrx.Mavericks
import org.koin.core.context.startKoin

class AutoSchoolApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)

        startKoin{
            modules(autoSchoolModule)
        }
    }
}