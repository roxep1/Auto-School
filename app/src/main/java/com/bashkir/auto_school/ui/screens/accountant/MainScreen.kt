package com.bashkir.auto_school.ui.screens.accountant

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.auto_school.data.models.Teacher
import com.bashkir.auto_school.ui.components.BaseTopBar
import com.bashkir.auto_school.ui.components.EmployeeList
import com.bashkir.auto_school.ui.components.StyledTextField
import com.bashkir.auto_school.ui.navigation.AccountantDestinations
import com.bashkir.auto_school.viewmodels.AccountantState
import com.bashkir.auto_school.viewmodels.AccountantViewModel

@ExperimentalMaterialApi
@Composable
fun MainScreenBody(viewModel: AccountantViewModel) = Scaffold(
    topBar = { BaseTopBar(titleText = "Работники") }
) {
    val emps by viewModel.collectAsState(AccountantState::employees)
    val openDialog = remember { mutableStateOf(false) }
    val salary = remember { mutableStateOf(TextFieldValue()) }
    val emp : MutableState<Teacher?> = remember { mutableStateOf(null) }

    if (emps is Success)
        EmployeeList(emps = emps()!!) {
            emp.value = it
            openDialog.value = true
        }

    if (openDialog.value)
        AlertDialogSubmit(openDialog = openDialog, salary = salary, employee = emp) {
            viewModel.changeSalary(emp.value!!.userInfo.phoneNumber.trim(), salary.value.text.toFloat())
            openDialog.value = false
        }
}

@Composable
private fun AlertDialogSubmit(
    openDialog: MutableState<Boolean>,
    salary: MutableState<TextFieldValue>,
    employee: MutableState<Teacher?>,
    onConfirm: () -> Unit
) =
    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        title = {
            Text("Изменение зарплаты сотрудника ${employee.value!!.userInfo}")
        },
        text = {
            StyledTextField(fieldState = salary, text = "Зарплата", KeyboardType.Number)
        },
        confirmButton = {
            Button(
                onClick = onConfirm
            ) { Text("Подтвердить") }
        })
