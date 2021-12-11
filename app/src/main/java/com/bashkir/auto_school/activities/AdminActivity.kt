package com.bashkir.auto_school.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.mvrx.compose.mavericksActivityViewModel
import com.bashkir.auto_school.ui.navigation.CreateAdminNavHost
import com.bashkir.auto_school.ui.theme.AutoSchoolTheme
import com.bashkir.auto_school.viewmodels.AdminViewModel

@ExperimentalMaterialApi
class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoSchoolTheme {
                StartAdminActivity()
            }
        }
    }

    @Composable
    private fun StartAdminActivity() {
        val navController = rememberNavController()
        val viewModel: AdminViewModel = mavericksActivityViewModel()
        CreateAdminNavHost(navController = navController, viewModel = viewModel)
    }
}