package com.bashkir.auto_school.ui.screens.student.sign_up

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.auto_school.data.models.Lesson
import com.bashkir.auto_school.ui.components.BaseTopBar
import com.bashkir.auto_school.ui.components.LessonList
import com.bashkir.auto_school.ui.navigation.StudentsSignUpDestinations
import com.bashkir.auto_school.viewmodels.StudentsState
import com.bashkir.auto_school.viewmodels.StudentsViewModel

@ExperimentalMaterialApi
@Composable
fun SignUpScreenBody(navController: NavController, viewModel: StudentsViewModel, teacherId: String) =
    Scaffold(
        topBar = {
            BaseTopBar(titleText = "Доступные занятия") {
                navController.navigate(StudentsSignUpDestinations.Teachers.name)
            }
        }
    ) {
        val openDialog = remember { mutableStateOf(false) }
        val selectedLesson: MutableState<Lesson?> = remember { mutableStateOf(null) }
        val lessons by viewModel.collectAsState(StudentsState::teacherLessons)
        if (lessons is Success)
            LessonList(lessons = lessons()!!, onClick = {
                selectedLesson.value = it
                openDialog.value = true
            })
        if (openDialog.value)
            AlertDialogSubmit(openDialog = openDialog, lesson = selectedLesson) {
                openDialog.value = false
                viewModel.signUpToLesson(selectedLesson.value!!, teacherId)
            }
    }

@Composable
private fun AlertDialogSubmit(
    openDialog: MutableState<Boolean>,
    lesson: MutableState<Lesson?>,
    onConfirm: () -> Unit
) =
    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        title = {
            Text("Хотите записаться на это занятие?")
        },
        text = {
            Text(
                "${lesson.value!!.date} " +
                        if (lesson.value!!.type.isDriving) "Вождение" else "Занятие по теории"
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm
            ) { Text("Подтвердить") }
        }
    )