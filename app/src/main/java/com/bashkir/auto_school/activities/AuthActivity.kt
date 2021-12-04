package com.bashkir.auto_school.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.airbnb.mvrx.compose.mavericksActivityViewModel
import com.bashkir.auto_school.ui.AuthScreenBody
import com.bashkir.auto_school.ui.theme.AutoSchoolTheme
import com.bashkir.auto_school.viewmodels.AuthViewModel

@ExperimentalMaterialApi
class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            StartAuthActivity {
                this.startActivity(Intent(this, it))
                this.finish()
            }
        }
    }

    @Composable
    fun StartAuthActivity(onAuth: (Class<*>) -> Unit) {
        AutoSchoolTheme {
            val viewModel: AuthViewModel = mavericksActivityViewModel()
            AuthScreenBody(viewModel, onAuth)
        }
    }
}