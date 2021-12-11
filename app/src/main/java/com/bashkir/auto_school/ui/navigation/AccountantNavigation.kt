package com.bashkir.auto_school.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.bashkir.auto_school.ui.navigation.AccountantDestinations.*
import com.bashkir.auto_school.ui.navigation.AccountantGraphs.MainGraph
import com.bashkir.auto_school.ui.screens.accountant.MainScreenBody
import com.bashkir.auto_school.viewmodels.AccountantViewModel

enum class AccountantGraphs {
    MainGraph
}

enum class AccountantDestinations {
    Main,
    Statistics
}

@ExperimentalMaterialApi
@Composable
fun CreateAccountantNavHost(navController: NavHostController, viewModel: AccountantViewModel) =
    NavHost(
        navController = navController,
        startDestination = MainGraph.name
    ) {
        mainGraph(viewModel)
    }

@ExperimentalMaterialApi
private fun NavGraphBuilder.mainGraph(
//    navController: NavController,
    viewModel: AccountantViewModel
) =
    navigation(startDestination = Main.name, route = MainGraph.name) {

        composable(Main.name){
            MainScreenBody(viewModel)
        }

        composable(Statistics.name){
            TODO()
        }
    }