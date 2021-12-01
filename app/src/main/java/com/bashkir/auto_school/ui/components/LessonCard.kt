package com.bashkir.auto_school.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bashkir.auto_school.Utils.translateMonth
import com.bashkir.auto_school.data.models.Lesson
import com.bashkir.auto_school.ui.theme.Green

@ExperimentalMaterialApi
@Composable
fun LessonCard(lesson: Lesson, onClick: () -> Unit = {}) =
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 8.dp,
        backgroundColor = Green,
        onClick = onClick
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
                    "${lesson.date.hour}:${if (lesson.date.minute.toString().length == 1) "0" + lesson.date.minute else lesson.date.minute}"
                )
            }
        }
    }

@ExperimentalMaterialApi
@Composable
fun LessonList(
    lessons: List<Lesson> = listOf(),
    onClick: (Lesson) -> Unit = {},
    lastItem: @Composable (LazyItemScope.() -> Unit) = {}
) =
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(lessons) { lesson ->
            LessonCard(lesson) { onClick(lesson) }
        }
        item(content = lastItem)
    }