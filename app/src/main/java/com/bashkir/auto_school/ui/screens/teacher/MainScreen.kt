package com.bashkir.auto_school.ui.screens.teacher

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.auto_school.Utils
import com.bashkir.auto_school.ui.components.BaseTopBar
import com.bashkir.auto_school.ui.components.LessonList
import com.bashkir.auto_school.ui.components.StyledButton
import com.bashkir.auto_school.ui.navigation.TeacherDestinations
import com.bashkir.auto_school.viewmodels.TeachersState
import com.bashkir.auto_school.viewmodels.TeachersViewModel

@ExperimentalMaterialApi
@Composable
fun MainScreenBody(navController: NavController, viewModel: TeachersViewModel, back: () -> Unit) = Scaffold(
    topBar = { BaseTopBar(titleText = "Расписание занятий") {
        back()
    } }
) {
    val lessons by viewModel.collectAsState(TeachersState::lessons)
    LessonList(if (lessons is Success) Utils.lessonsAfterNow(lessons()!!) else listOf(), onClick = {
        navController.navigate(TeacherDestinations.DetailLesson.getDest(it.id.toString()))
    }) {
        StyledButton(text = "Создать занятие") {
            navController.navigate(TeacherDestinations.CreateLesson.name)
        }
    }
}