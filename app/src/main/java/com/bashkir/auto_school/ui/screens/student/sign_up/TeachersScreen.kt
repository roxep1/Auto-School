package com.bashkir.auto_school.ui.screens.student.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.auto_school.data.models.Teacher
import com.bashkir.auto_school.ui.components.BaseTopBar
import com.bashkir.auto_school.ui.navigation.StudentsGraphs
import com.bashkir.auto_school.ui.navigation.StudentsSignUpDestinations
import com.bashkir.auto_school.ui.theme.Green
import com.bashkir.auto_school.viewmodels.StudentsState
import com.bashkir.auto_school.viewmodels.StudentsViewModel

@ExperimentalMaterialApi
@Composable
fun TeachersScreenBody(navController: NavController, viewModel: StudentsViewModel) = Scaffold(
    topBar = {
        BaseTopBar(titleText = "Инструктора") {
            navController.navigate(StudentsGraphs.MainGraph.name)
        }
    }
) {
    val teachers by viewModel.collectAsState(StudentsState::teachers)
    if (teachers is Success)
        TeachersList(teachers()!!) {
            viewModel.getTeacherLessons(it)
            navController.navigate("${StudentsSignUpDestinations.SignUp.name}/${it.userInfo.phoneNumber}")
        }
}

@ExperimentalMaterialApi
@Composable
private fun TeachersList(teachers: List<Teacher>, navigate: (Teacher) -> Unit) =
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(teachers) { teacher ->
            TeacherCard(teacher) {
                navigate(teacher)
            }
        }
    }


@ExperimentalMaterialApi
@Composable
private fun TeacherCard(teacher: Teacher, navigate: () -> Unit) =
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 8.dp,
        backgroundColor = Green,
        onClick = navigate
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("${teacher.userInfo}")
            Text(teacher.positionName)
        }
    }