package com.bashkir.auto_school.ui.student

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bashkir.auto_school.ui.student.main.MainScreenBody

enum class StudentsGraphs {
    MainGraph,
    SignUpGraph
}

enum class StudentsMainDestinations {
    History,
    Main,
    Settings
}

enum class StudentsSignUpDestinations {
    SignUp,
    Teachers
}

@Composable
fun CreateStudentsNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = StudentsGraphs.MainGraph.name
    ) {
        mainGraph(navController)
        signUpGraph(navController)
    }
}

private fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(StudentsMainDestinations.Main.name, StudentsGraphs.MainGraph.name) {

        composable(StudentsMainDestinations.Main.name) {
            MainScreenBody(navController)
        }

        composable(StudentsMainDestinations.History.name) {

        }

        composable(StudentsMainDestinations.Settings.name) {

        }
    }
}

private fun NavGraphBuilder.signUpGraph(navController: NavController) {
    navigation(StudentsSignUpDestinations.Teachers.name, StudentsGraphs.SignUpGraph.name) {

        composable(StudentsSignUpDestinations.Teachers.name) {

        }

        composable(StudentsSignUpDestinations.SignUp.name) {

        }
    }
}