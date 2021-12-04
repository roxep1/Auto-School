package com.bashkir.auto_school.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bashkir.auto_school.ui.screens.student.main.HistoryScreenBody
import com.bashkir.auto_school.ui.screens.student.main.MainScreenBody
import com.bashkir.auto_school.ui.screens.student.sign_up.SignUpScreenBody
import com.bashkir.auto_school.ui.screens.student.sign_up.TeachersScreenBody
import com.bashkir.auto_school.viewmodels.StudentsViewModel

enum class StudentsGraphs {
    MainGraph,
    SignUpGraph
}

enum class StudentsMainDestinations {
    History,
    Main,
    Settings
}

enum class StudentsSignUpDestinations(val argument : String = "") {
    SignUp("teacherId"),
    Teachers;
    val destWithArgument = "${name}/{$argument}"
}

@ExperimentalMaterialApi
@Composable
fun CreateStudentsNavHost(navController: NavHostController, viewModel: StudentsViewModel) {
    NavHost(
        navController = navController,
        startDestination = StudentsGraphs.MainGraph.name
    ) {
        mainGraph(navController, viewModel)
        signUpGraph(navController, viewModel)
    }
}

@ExperimentalMaterialApi
private fun NavGraphBuilder.mainGraph(navController: NavController, viewModel: StudentsViewModel) =
    navigation(StudentsMainDestinations.Main.name, StudentsGraphs.MainGraph.name) {

        composable(StudentsMainDestinations.Main.name) {
            MainScreenBody(navController, viewModel)
        }

        composable(StudentsMainDestinations.History.name) {
            HistoryScreenBody(navController, viewModel)
        }

        composable(StudentsMainDestinations.Settings.name) {

        }
    }


@ExperimentalMaterialApi
private fun NavGraphBuilder.signUpGraph(navController: NavController, viewModel: StudentsViewModel) =
    navigation(StudentsSignUpDestinations.Teachers.name, StudentsGraphs.SignUpGraph.name) {

        composable(StudentsSignUpDestinations.Teachers.name) {
            TeachersScreenBody(navController, viewModel)
        }

        composable(StudentsSignUpDestinations.SignUp.destWithArgument) {
            SignUpScreenBody(navController, viewModel, it.arguments?.getString(StudentsSignUpDestinations.SignUp.argument)?:"")
        }
    }
