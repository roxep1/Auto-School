package com.bashkir.auto_school.ui.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.bashkir.auto_school.Utils
import com.bashkir.auto_school.Utils.toLocalDateTime
import com.bashkir.auto_school.data.models.*


@ExperimentalMaterialApi
@Composable
fun UserForm(
    tariffs: List<Tariff>,
    positions: List<String>,
    create: (User, Student?, Teacher?) -> Unit
) {
    val options = Role.values().toList()

    val phoneNumber = remember { mutableStateOf(TextFieldValue()) }
    val name = remember { mutableStateOf(TextFieldValue()) }
    val lastName = remember { mutableStateOf(TextFieldValue()) }
    val middleName = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val login = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }

    val rolesExpanded = remember { mutableStateOf(false) }
    val tariffsExpanded = remember { mutableStateOf(false) }
    val positionsExpanded = remember { mutableStateOf(false) }

    val datePickerText = remember {
        mutableStateOf("Дата конца обучения")
    }

    val salary = remember { mutableStateOf(TextFieldValue()) }
    val coef = remember { mutableStateOf(TextFieldValue()) }

    val selectedRole =
        remember { mutableStateOf(if (options.isNotEmpty()) options.first() else null) }
    val selectedTariff =
        remember { mutableStateOf(if (tariffs.isNotEmpty()) tariffs.first() else null) }
    val selectedPosition =
        remember { mutableStateOf(if (positions.isNotEmpty()) positions.first() else null) }

    val elements = arrayListOf<@Composable () -> Unit>(
        { StyledTextField(fieldState = phoneNumber, text = "Номер телефона") },
        { StyledTextField(fieldState = name, text = "Имя") },
        { StyledTextField(fieldState = lastName, text = "Фамилия") },
        { StyledTextField(fieldState = middleName, text = "Отчество") },
        { StyledTextField(fieldState = email, text = "Почта") },
        { StyledTextField(fieldState = login, text = "Логин") },
        { StyledTextField(fieldState = password, text = "Пароль") },
        { ComboBox(options = options, expanded = rolesExpanded, selectedOption = selectedRole) })

    if (selectedRole.value == Role.STUDENT)
        elements.addAll(listOf(
            {
                DatePickerView(text = datePickerText) {
                    datePickerText.value = Utils.fromLongToLocalDateTimeString(it)
                }
            },
            {
                ComboBox(
                    options = tariffs,
                    expanded = tariffsExpanded,
                    selectedOption = selectedTariff
                )
            }
        ))
    else elements.addAll(listOf({
        StyledTextField(salary, "Зарплата", KeyboardType.Number)
    }, {
        StyledTextField(fieldState = coef, text = "Ставка", KeyboardType.Number)
    }, {
        ComboBox(
            options = positions,
            expanded = positionsExpanded,
            selectedOption = selectedPosition
        )
    }
    ))

    elements.add {
        StyledButton(
            text = "Создать",
            enabled = tariffs.isNotEmpty() && positions.isNotEmpty()
        ) {
            val user = User(
                phoneNumber.value.text,
                name.value.text,
                lastName.value.text,
                middleName.value.text,
                email.value.text,
                Cred(login.value.text, password.value.text)
            )
            create(
                user,
                if (selectedRole.value == Role.STUDENT)
                    Student(
                        user,
                        datePickerText.value.toLocalDateTime().toLocalDate(),
                        selectedTariff.value!!
                    )
                else null,
                if (selectedRole.value != Role.STUDENT)
                    Teacher(
                        selectedPosition.value!!,
                        user,
                        salary.value.text.toFloat()
                    )
                else null
            )
        }
    }

    Form(elements)
}