package com.bashkir.auto_school.ui.screens.teacher

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.auto_school.ui.components.BaseTopBar
import com.bashkir.auto_school.ui.components.LessonCard
import com.bashkir.auto_school.ui.components.UserList
import com.bashkir.auto_school.ui.navigation.TeacherDestinations.Main
import com.bashkir.auto_school.viewmodels.TeachersState
import com.bashkir.auto_school.viewmodels.TeachersViewModel

@ExperimentalMaterialApi
@Composable
fun DetailLessonScreenBody(
    navController: NavController,
    viewModel: TeachersViewModel,
    lessonId: Int
) = Scaffold(
    topBar = {
        BaseTopBar(titleText = "Занятие номер $lessonId") {
            navController.navigate(Main.name)
        }
    }
) {
    val lessons by viewModel.collectAsState(TeachersState::lessons)
    if (lessons is Success) {
        val lesson = lessons()!!.first { it.id!! == lessonId }
        val students = lesson.students
        Column {
            LessonCard(lesson = lesson)

            if (students != null)
                UserList(users = students.map { it.userInfo })
        }
    }
}