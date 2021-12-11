package com.bashkir.auto_school.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.bashkir.auto_school.ui.navigation.AdminDestinations.CreateUser
import com.bashkir.auto_school.ui.navigation.AdminDestinations.Main
import com.bashkir.auto_school.ui.navigation.AdminGraphs.MainGraph
import com.bashkir.auto_school.ui.screens.admin.CreateUserScreenBody
import com.bashkir.auto_school.ui.screens.admin.MainScreenBody
import com.bashkir.auto_school.viewmodels.AdminViewModel

enum class AdminGraphs {
    MainGraph
}

enum class AdminDestinations() {
    Main,
    CreateUser
}

@ExperimentalMaterialApi
@Composable
fun CreateAdminNavHost(navController: NavHostController, viewModel: AdminViewModel) = NavHost(
    navController = navController,
    startDestination = MainGraph.name
) {
    mainGraph(navController, viewModel)
}

@ExperimentalMaterialApi
private fun NavGraphBuilder.mainGraph(navController: NavController, viewModel: AdminViewModel) =
    navigation(startDestination = Main.name, route = MainGraph.name) {

        composable(Main.name) {
            MainScreenBody(navController, viewModel)
        }

        composable(CreateUser.name) {
            CreateUserScreenBody(navController = navController, viewModel = viewModel)
        }
    }
