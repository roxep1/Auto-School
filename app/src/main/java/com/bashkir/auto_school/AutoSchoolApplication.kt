package com.bashkir.auto_school

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.navigation.DefaultNavigationViewModelDelegateFactory
import org.koin.core.context.startKoin

class AutoSchoolApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this, viewModelDelegateFactory = DefaultNavigationViewModelDelegateFactory())

        startKoin{
            modules(studentsModule)
        }
    }
}