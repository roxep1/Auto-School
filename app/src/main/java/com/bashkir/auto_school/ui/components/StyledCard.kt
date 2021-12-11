package com.bashkir.auto_school.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bashkir.auto_school.ui.theme.Green

@ExperimentalMaterialApi
@Composable
fun StyledCard(onClick: () -> Unit = {},content: @Composable () -> Unit) = Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
    elevation = 8.dp,
    backgroundColor = Green,
    content = content,
    onClick = onClick
)