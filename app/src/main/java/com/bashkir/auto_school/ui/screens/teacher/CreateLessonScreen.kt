package com.bashkir.auto_school.ui.screens.teacher

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.auto_school.Utils
import com.bashkir.auto_school.data.models.Lesson
import com.bashkir.auto_school.data.models.LessonType
import com.bashkir.auto_school.ui.components.*
import com.bashkir.auto_school.ui.navigation.TeacherDestinations
import com.bashkir.auto_school.viewmodels.TeachersState
import com.bashkir.auto_school.viewmodels.TeachersViewModel
import java.time.Instant

@ExperimentalMaterialApi
@Composable
fun CreateLessonScreenBody(navController: NavController, viewModel: TeachersViewModel) = Scaffold(
    topBar = {
        BaseTopBar(titleText = "Создать занятие") {
            navController.navigate(TeacherDestinations.Main.name)
        }
    },
    modifier = Modifier.fillMaxSize()
) {
    val lessonTypes by viewModel.collectAsState(TeachersState::lessonTypes)
    if (lessonTypes is Success)
        CreateLessonForm(lessonTypes()!!) {
            viewModel.createLesson(it)
            navController.navigate(TeacherDestinations.Main.name)
        }
}

@ExperimentalMaterialApi
@Composable
fun CreateLessonForm(lessonTypes: List<LessonType>, onSubmit: (Lesson) -> Unit) {
    val datePickerText = remember {
        mutableStateOf("Дата занятия")
    }

    val expanded = remember {
        mutableStateOf(false)
    }

    val selectedType = remember {
        mutableStateOf(if (lessonTypes.isNotEmpty()) lessonTypes.first() else null)
    }

    Form(
        listOf({
            DatePickerView(text = datePickerText) { date: Long? ->
                datePickerText.value = Utils.fromLongToLocalDateTimeString(date)
            }
        }, {
            ComboBox(lessonTypes, expanded, selectedType)
        }, {
            StyledButton(text = "Создать", enabled = selectedType.value != null) {
                onSubmit(
                    Lesson(
                        dateString = Instant.now().toString(),
                        type = selectedType.value!!
                    )
                )
            }
        })
    )
}
