package com.bashkir.auto_school.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bashkir.auto_school.ui.theme.Green

@Composable
fun ListButton(text: String, action: () -> Unit) =
    Button(
        onClick = action,
        colors = ButtonDefaults.textButtonColors(Green, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 40.dp, top = 16.dp, bottom = 16.dp)
            .height(40.dp)
    ) {
        Text(text, fontSize = 16.sp)
    }