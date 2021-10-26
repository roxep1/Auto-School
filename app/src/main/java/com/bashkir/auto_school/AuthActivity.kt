package com.bashkir.auto_school

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bashkir.auto_school.student.StudentActivity
import com.bashkir.auto_school.ui.AuthScreenBody
import com.bashkir.auto_school.ui.theme.AutoSchoolTheme

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            StartAuthActivity {
                startActivity(Intent(this, StudentActivity::class.java))
            }
        }
    }

    @Composable
    fun StartAuthActivity(onClickAuth: () -> Unit) {
        AutoSchoolTheme {
            AuthScreenBody(onClickAuth)
//            val navController = rememberNavController()
//            CreateNavHost(navController = navController)
        }
    }

    @Preview
    @Composable
    fun StartAuthPreview() {
        AutoSchoolTheme {
            AuthScreenBody {}
        }
    }
}