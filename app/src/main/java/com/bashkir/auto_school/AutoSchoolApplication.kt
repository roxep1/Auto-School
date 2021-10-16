package com.bashkir.auto_school

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.navigation.DefaultNavigationViewModelDelegateFactory

class AutoSchoolApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this, viewModelDelegateFactory = DefaultNavigationViewModelDelegateFactory())
    }
}