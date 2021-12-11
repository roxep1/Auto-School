package com.bashkir.auto_school.ui.screens.admin

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.auto_school.ui.components.BaseTopBar
import com.bashkir.auto_school.ui.components.UserForm
import com.bashkir.auto_school.ui.navigation.AdminDestinations.Main
import com.bashkir.auto_school.viewmodels.AdminState
import com.bashkir.auto_school.viewmodels.AdminViewModel

@ExperimentalMaterialApi
@Composable
fun CreateUserScreenBody(navController: NavController, viewModel: AdminViewModel) = Scaffold(
    topBar = {
        BaseTopBar(titleText = "Создание пользователя") {
            navController.navigate(Main.name)
        }
    }
) {
    val tariffs by viewModel.collectAsState(AdminState::tariffs)
    val positions by viewModel.collectAsState(AdminState::positions)
    if (tariffs is Success && positions is Success)
        UserForm(tariffs()!!, positions()!!) { user, student, emp ->
            when {
                student != null -> viewModel.createStudent(user, student)
                emp != null -> viewModel.createEmployee(user, emp)
                else -> throw Exception()
            }
            navController.navigate(Main.name)
        }
}

