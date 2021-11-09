package com.bashkir.auto_school.ui.student.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.auto_school.R
import com.bashkir.auto_school.models.Lesson
import com.bashkir.auto_school.models.LessonType
import com.bashkir.auto_school.other.Utils.translateMonth
import com.bashkir.auto_school.ui.student.StudentsGraphs
import com.bashkir.auto_school.ui.student.StudentsMainDestinations
import com.bashkir.auto_school.ui.theme.Gray
import com.bashkir.auto_school.ui.theme.Green
import com.bashkir.auto_school.viewmodels.StudentsViewModel
import java.time.LocalDateTime

@Composable
fun MainScreenBody(navController: NavController, viewModel: StudentsViewModel) =
    Scaffold(
        topBar = { MainTopBar(navController) }
    ) {
//        val lessons by viewModel.collectAsState(StudentsState::lessons)
//        ShowLessons(lessons = lessons, navController)
    }

@Composable
private fun MainTopBar(navController: NavController) =
    TopAppBar(
        elevation = 4.dp,
        title = { Text("Записи на занятия") },
        backgroundColor = Gray,
        actions = {
            IconButton(onClick = { navController.navigate(StudentsMainDestinations.Settings.name) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_carbw),
                    contentDescription = "History button"
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = { navController.navigate(StudentsMainDestinations.History.name) }) {
                Icon(Icons.Filled.Settings, "Settings button")
            }

        }
    )

@Composable
private fun ShowLessons(lessons: Async<List<Lesson>>, navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        if (lessons is Success)
            items(lessons()) { lesson ->
                LessonCard(lesson)
            }
        item {
            Button(
                onClick = { navController.navigate(StudentsGraphs.SignUpGraph.name) },
                colors = ButtonDefaults.textButtonColors(Green),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Записаться на занятие")
            }
        }
    }
}

@Composable
private fun LessonCard(lesson: Lesson) =
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp,
        backgroundColor = Green
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text("${lesson.date.dayOfMonth} ${translateMonth(lesson.date.month)}")
                Text(if (lesson.type.isDriving) "Занятие с инструктором" else "Теоретическое занятие")
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    "${lesson.date.hour}:${if (lesson.date.minute.toString().length == 1) "0" + lesson.date.minute else lesson.date.minute}",
                    modifier = Modifier
                )
            }
        }
    }

@Composable
fun LessonCardPreview() {
    LessonCard(lesson = Lesson(LocalDateTime.now(), LessonType("Вождение", true), ""))
}

