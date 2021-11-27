package com.bashkir.auto_school.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bashkir.auto_school.ui.theme.Gray

@Composable
fun BaseTopBar(titleText: String, navigateBack: () -> Unit) =
    TopAppBar (
        elevation = 4.dp,
        title = {
            Text(titleText)
        },
        backgroundColor = Gray,
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(Icons.Filled.ArrowBack, "Back button")
            }
        }
    )