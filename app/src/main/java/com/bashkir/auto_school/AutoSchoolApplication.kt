package com.bashkir.auto_school

import android.app.Application
import androidx.compose.material.ExperimentalMaterialApi
import com.airbnb.mvrx.Mavericks
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@ExperimentalMaterialApi
class AutoSchoolApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)

        startKoin{
            androidContext(this@AutoSchoolApplication)
            modules(autoSchoolModule)
        }
    }
}