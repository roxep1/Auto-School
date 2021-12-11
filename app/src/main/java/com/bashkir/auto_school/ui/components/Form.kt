package com.bashkir.auto_school.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Form(content: List<@Composable () -> Unit>) =
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(content) { element ->
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                element()
            }
        }
    }
