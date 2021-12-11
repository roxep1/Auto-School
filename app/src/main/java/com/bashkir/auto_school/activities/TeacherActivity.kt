package com.bashkir.auto_school.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.mvrx.compose.mavericksActivityViewModel
import com.bashkir.auto_school.ui.navigation.CreateTeacherNavHost
import com.bashkir.auto_school.ui.theme.AutoSchoolTheme
import com.bashkir.auto_school.viewmodels.TeachersViewModel

@ExperimentalMaterialApi
class TeacherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AutoSchoolTheme {
                StartTeacherActivity()
            }
        }
    }

    @Composable
    private fun StartTeacherActivity() {
        val navController = rememberNavController()
        val viewModel: TeachersViewModel = mavericksActivityViewModel()
        CreateTeacherNavHost(navController, viewModel)
    }
}