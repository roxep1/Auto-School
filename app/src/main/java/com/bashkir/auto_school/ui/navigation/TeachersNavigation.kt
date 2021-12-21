package com.bashkir.auto_school.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.bashkir.auto_school.ui.navigation.TeacherDestinations.*
import com.bashkir.auto_school.ui.navigation.TeacherGraphs.MainGraph
import com.bashkir.auto_school.ui.screens.teacher.CreateLessonScreenBody
import com.bashkir.auto_school.ui.screens.teacher.DetailLessonScreenBody
import com.bashkir.auto_school.ui.screens.teacher.MainScreenBody
import com.bashkir.auto_school.viewmodels.TeachersViewModel

enum class TeacherGraphs {
    MainGraph
}

enum class TeacherDestinations(val argument: String? = null) {
    Main,
    DetailLesson("lessonId"),
    CreateLesson;

    val destWithArgument = "${name}/{$argument}"

    fun getDest(argument: String) = "$name/$argument"
}

@ExperimentalMaterialApi
@Composable
fun CreateTeacherNavHost(navController: NavHostController, viewModel: TeachersViewModel, back: () -> Unit) =
    NavHost(
        navController = navController,
        startDestination = MainGraph.name
    ) {
        mainGraph(navController, viewModel, back)
    }

@ExperimentalMaterialApi
private fun NavGraphBuilder.mainGraph(navController: NavController, viewModel: TeachersViewModel, back: () -> Unit) =
    navigation(Main.name, MainGraph.name) {

        composable(Main.name) {
            MainScreenBody(navController, viewModel, back)
        }

        composable(DetailLesson.destWithArgument) {
            DetailLessonScreenBody(navController, viewModel, it.getArgument(DetailLesson).toInt())
        }

        composable(CreateLesson.name) {
            CreateLessonScreenBody(navController, viewModel)
        }
    }

private fun NavBackStackEntry.getArgument(dest: TeacherDestinations) =
    arguments?.getString(dest.argument) ?: ""
