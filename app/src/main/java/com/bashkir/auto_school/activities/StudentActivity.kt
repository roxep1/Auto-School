package com.bashkir.auto_school.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.mvrx.compose.mavericksActivityViewModel
import com.bashkir.auto_school.ui.navigation.CreateStudentsNavHost
import com.bashkir.auto_school.ui.theme.AutoSchoolTheme
import com.bashkir.auto_school.viewmodels.StudentsViewModel

class StudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            AutoSchoolTheme {
                StartStudentActivity()
            }
        }
    }
    @Composable
    fun StartStudentActivity() {
        val navController = rememberNavController()
        val viewModel: StudentsViewModel = mavericksActivityViewModel()
        CreateStudentsNavHost(navController, viewModel)
    }
}
