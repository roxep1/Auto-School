package com.bashkir.auto_school.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.bashkir.auto_school.ui.navigation.HRDestinations.Main
import com.bashkir.auto_school.ui.navigation.HRDestinations.Vacations
import com.bashkir.auto_school.ui.navigation.HRGraphs.MainGraph
import com.bashkir.auto_school.ui.screens.hr.MainScreenBody
import com.bashkir.auto_school.ui.screens.hr.VacationsScreenBody
import com.bashkir.auto_school.viewmodels.HumanResViewModel

enum class HRGraphs {
    MainGraph
}

enum class HRDestinations() {
    Main,
    Vacations
}

@ExperimentalMaterialApi
@Composable
fun CreateHumanResNavHost(navController: NavHostController, viewModel: HumanResViewModel) = NavHost(
    navController = navController,
    startDestination = MainGraph.name
) {
    mainGraph(navController, viewModel)
}

@ExperimentalMaterialApi
private fun NavGraphBuilder.mainGraph(navController: NavController, viewModel: HumanResViewModel) =
    navigation(startDestination = Main.name, route = MainGraph.name) {

        composable(Main.name) {
            MainScreenBody(navController, viewModel)
        }

        composable(Vacations.name) {
            VacationsScreenBody(navController, viewModel)
        }
    }