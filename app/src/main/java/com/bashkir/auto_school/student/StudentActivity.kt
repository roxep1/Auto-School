package com.bashkir.auto_school.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.airbnb.mvrx.compose.mavericksActivityViewModel
import com.bashkir.auto_school.ui.student.CreateStudentsNavHost
import com.bashkir.auto_school.ui.student.main.MainScreenBody
import com.bashkir.auto_school.ui.theme.AutoSchoolTheme
import com.bashkir.auto_school.viewmodels.StudentsViewModel

class StudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            StartStudentActivity()
        }
    }
    @Composable
    fun StartStudentActivity() {
        val navController = rememberNavController()
        val viewModel: StudentsViewModel = mavericksActivityViewModel()
        CreateStudentsNavHost(navController, viewModel)
    }
}
