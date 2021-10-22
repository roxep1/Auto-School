package com.bashkir.auto_school.ui.student.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bashkir.auto_school.R
import com.bashkir.auto_school.ui.student.StudentsMainDestinations
import com.bashkir.auto_school.ui.theme.Gray

@Composable
fun MainScreenBody(navController: NavController) {
    Scaffold(
        topBar = { MainTopBar(navController) }
    ) {

    }
}

@Composable
fun MainTopBar(navController: NavController) {
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
                Icon(Icons.Filled.Settings, "Settings button" )
            }

        }
    )
}