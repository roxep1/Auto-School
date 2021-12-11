package com.bashkir.auto_school.ui.screens.hr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.auto_school.data.models.Teacher
import com.bashkir.auto_school.data.models.Vacation
import com.bashkir.auto_school.ui.components.BaseTopBar
import com.bashkir.auto_school.ui.components.StyledCard
import com.bashkir.auto_school.ui.navigation.HRDestinations
import com.bashkir.auto_school.viewmodels.HumanResState
import com.bashkir.auto_school.viewmodels.HumanResViewModel

@ExperimentalMaterialApi
@Composable
fun VacationsScreenBody(navController: NavController, viewModel: HumanResViewModel) = Scaffold(
    topBar = {
        BaseTopBar("Отпуска") {
            navController.navigate(HRDestinations.Main.name)
        }
    }
) {
    val vacations by viewModel.collectAsState(HumanResState::vacations)
    val employees by viewModel.collectAsState(HumanResState::employees)

    if (vacations is Success && employees is Success)
        VacationList(vacations()!!, employees()!!)
}

@ExperimentalMaterialApi
@Composable
private fun VacationList(vacations: List<Vacation>, employees: List<Teacher>) = LazyColumn {
    items(vacations) { vacation ->
        VacationCard(vacation, employees.first { it.userInfo.phoneNumber == vacation.phoneNumber })
    }
}

@ExperimentalMaterialApi
@Composable
private fun VacationCard(vacation: Vacation, employee: Teacher) = StyledCard {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            Text("${employee.userInfo}")
        }

        Column {
            Text(vacation.periodOfVacation)
        }
    }
}