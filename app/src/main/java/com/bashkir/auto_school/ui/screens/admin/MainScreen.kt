package com.bashkir.auto_school.ui.screens.admin

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.auto_school.ui.components.BaseTopBar
import com.bashkir.auto_school.ui.components.StyledButton
import com.bashkir.auto_school.ui.components.UserList
import com.bashkir.auto_school.ui.navigation.AdminDestinations.CreateUser
import com.bashkir.auto_school.viewmodels.AdminState
import com.bashkir.auto_school.viewmodels.AdminViewModel

@ExperimentalMaterialApi
@Composable
fun MainScreenBody(navController: NavController, viewModel: AdminViewModel) = Scaffold(
    topBar = { BaseTopBar(titleText = "Пользователи") }
) {
    val users by viewModel.collectAsState(AdminState::users)
    UserList(if (users is Success) users()!! else listOf()) {
        StyledButton(text = "Создать пользователя") {
            navController.navigate(CreateUser.name)
        }
    }
}