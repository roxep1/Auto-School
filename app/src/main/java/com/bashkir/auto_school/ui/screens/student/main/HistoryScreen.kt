package com.bashkir.auto_school.ui.screens.student.main

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.auto_school.ui.components.BaseTopBar
import com.bashkir.auto_school.ui.components.LessonList
import com.bashkir.auto_school.ui.components.ListButton
import com.bashkir.auto_school.ui.navigation.StudentsMainDestinations
import com.bashkir.auto_school.viewmodels.StudentsState
import com.bashkir.auto_school.viewmodels.StudentsViewModel

@ExperimentalMaterialApi
@Composable
fun HistoryScreenBody(navController: NavController, viewModel: StudentsViewModel) = Scaffold(
    topBar = {
        BaseTopBar(titleText = "История занятий") {
            navController.navigate(StudentsMainDestinations.Main.name)
        }
    }
) {
    val lessons by viewModel.collectAsState(StudentsState::lessons)
    if (lessons is Success)
        lessons()?.let {
            LessonList(it) {
                ListButton("Очистить историю", viewModel::clearHistory)
            }
        }
}

