package com.bashkir.auto_school.ui.screens.hr

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.auto_school.data.models.Teacher
import com.bashkir.auto_school.ui.components.BaseTopBar
import com.bashkir.auto_school.ui.components.EmployeeList
import com.bashkir.auto_school.ui.components.StyledButton
import com.bashkir.auto_school.ui.components.StyledTextField
import com.bashkir.auto_school.ui.navigation.HRDestinations.Vacations
import com.bashkir.auto_school.viewmodels.HumanResState
import com.bashkir.auto_school.viewmodels.HumanResViewModel

@ExperimentalMaterialApi
@Composable
fun MainScreenBody(navController: NavController, viewModel: HumanResViewModel) = Scaffold(
    topBar = { BaseTopBar(titleText = "Работники") }
) {
    val users by viewModel.collectAsState(HumanResState::employees)
    val openDialog = remember { mutableStateOf(false) }
    val selectedEmployee: MutableState<Teacher?> = remember { mutableStateOf(null) }
    val periodOfVacation = remember { mutableStateOf(TextFieldValue()) }
    val coef = remember { mutableStateOf(TextFieldValue()) }

    if (users is Success)
        EmployeeList(emps = users()!!, lastItem = {
            StyledButton("Все отпуска") {
                navController.navigate(Vacations.name)
            }
        }) {
            selectedEmployee.value = it
            openDialog.value = true
        }

    if (openDialog.value)
        AlertDialogSubmit(
            openDialog = openDialog,
            coef = coef,
            periodOfVacation = periodOfVacation,
            employee = selectedEmployee
        ) {
            viewModel.createVacation(
                coef.value.text.toFloat(),
                periodOfVacation.value.text,
                selectedEmployee.value!!.userInfo.phoneNumber
            )
        }
}

@Composable
private fun AlertDialogSubmit(
    openDialog: MutableState<Boolean>,
    coef: MutableState<TextFieldValue>,
    periodOfVacation: MutableState<TextFieldValue>,
    employee: MutableState<Teacher?>,
    onConfirm: () -> Unit
) =
    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        title = {
            Text("Добавить отпуск сотруднику ${employee.value!!.userInfo}")
        },
        text = {
            StyledTextField(fieldState = coef, text = "Коэффициент", KeyboardType.Number)
            Spacer(Modifier.height(10.dp))
            StyledTextField(
                fieldState = periodOfVacation,
                text = "Период отпуска",
                KeyboardType.Text
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm
            ) { Text("Подтвердить") }
        })

