package com.bashkir.auto_school.student.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.bashkir.auto_school.ui.student.StudentsGraphs
import com.bashkir.auto_school.ui.student.CreateStudentsNavHost
import com.bashkir.auto_school.ui.student.main.MainScreenBody
import com.bashkir.auto_school.ui.theme.AutoSchoolTheme

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
        CreateStudentsNavHost(navController)
//        navController.navigate(StudentsGraphs.MainGraph.name)
    }

    @Preview
    @Composable
    fun Check(){
        AutoSchoolTheme {
            MainScreenBody(navController = rememberNavController())
        }
    }
}
