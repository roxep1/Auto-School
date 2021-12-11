package com.bashkir.auto_school.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bashkir.auto_school.ui.navigation.StudentsGraphs.*
import com.bashkir.auto_school.ui.navigation.StudentsMainDestinations.*
import com.bashkir.auto_school.ui.navigation.StudentsSignUpDestinations.*
import com.bashkir.auto_school.ui.screens.student.main.HistoryScreenBody
import com.bashkir.auto_school.ui.screens.student.main.MainScreenBody
import com.bashkir.auto_school.ui.screens.student.sign_up.SignUpScreenBody
import com.bashkir.auto_school.ui.screens.student.sign_up.TeachersScreenBody
import com.bashkir.auto_school.viewmodels.StudentsViewModel
import org.koin.core.instance.getArguments

enum class StudentsGraphs {
    MainGraph,
    SignUpGraph
}

enum class StudentsMainDestinations {
    History,
    Main,
    Settings
}

enum class StudentsSignUpDestinations(val argument : String? = null) {
    SignUp("teacherId"),
    Teachers;
    val destWithArgument = "${name}/{$argument}"
    fun getDest(argument: String) = "$name/$argument"
}

@ExperimentalMaterialApi
@Composable
fun CreateStudentsNavHost(navController: NavHostController, viewModel: StudentsViewModel) {
    NavHost(
        navController = navController,
        startDestination = MainGraph.name
    ) {
        mainGraph(navController, viewModel)
        signUpGraph(navController, viewModel)
    }
}

@ExperimentalMaterialApi
private fun NavGraphBuilder.mainGraph(navController: NavController, viewModel: StudentsViewModel) =
    navigation(Main.name, MainGraph.name) {

        composable(Main.name) {
            MainScreenBody(navController, viewModel)
        }

        composable(History.name) {
            HistoryScreenBody(navController, viewModel)
        }

        composable(Settings.name) {

        }
    }


@ExperimentalMaterialApi
private fun NavGraphBuilder.signUpGraph(navController: NavController, viewModel: StudentsViewModel) =
    navigation(Teachers.name, SignUpGraph.name) {

        composable(Teachers.name) {
            TeachersScreenBody(navController, viewModel)
        }

        composable(SignUp.destWithArgument) {
            SignUpScreenBody(navController, viewModel, it.getArgument(SignUp))
        }
    }

private fun NavBackStackEntry.getArgument(dest : StudentsSignUpDestinations) =
    arguments?.getString(dest.argument)?:""