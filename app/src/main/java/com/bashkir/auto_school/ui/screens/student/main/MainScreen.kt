package com.bashkir.auto_school.ui.screens.student.main

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.auto_school.R
import com.bashkir.auto_school.Utils.lessonsAfterNow
import com.bashkir.auto_school.ui.components.LessonList
import com.bashkir.auto_school.ui.components.StyledButton
import com.bashkir.auto_school.ui.navigation.StudentsGraphs
import com.bashkir.auto_school.ui.navigation.StudentsMainDestinations
import com.bashkir.auto_school.ui.theme.Gray
import com.bashkir.auto_school.viewmodels.StudentsState
import com.bashkir.auto_school.viewmodels.StudentsViewModel

@ExperimentalMaterialApi
@Composable
fun MainScreenBody(navController: NavController, viewModel: StudentsViewModel) =
    Scaffold(
        topBar = { MainTopBar(navController) }
    ) {
        val lessons by viewModel.collectAsState(StudentsState::lessons)
        LessonList(
            lessons = if (lessons is Success) lessonsAfterNow(lessons()!!) else listOf()
        ) {
            StyledButton("Записаться на занятие") {
                navController.navigate(StudentsGraphs.SignUpGraph.name)
            }
        }
    }

@Composable
private fun MainTopBar(navController: NavController) =
    TopAppBar(
        elevation = 4.dp,
        title = { Text("Записи на занятия") },
        backgroundColor = Gray,
        actions = {
            HistoryIcon { navController.navigate(StudentsMainDestinations.History.name) }
//            Spacer(modifier = Modifier.width(16.dp))
//            SettingsIcon { navController.navigate(StudentsMainDestinations.Settings.name) }
        }
    )

@Composable
private fun HistoryIcon(navigate: () -> Unit) =
    IconButton(onClick = navigate) {
        Icon(
            painter = painterResource(id = R.drawable.ic_carbw),
            contentDescription = "History button"
        )
    }

@Composable
private fun SettingsIcon(navigate: () -> Unit) =
    IconButton(onClick = navigate) {
        Icon(Icons.Filled.Settings, "Settings button")
    }