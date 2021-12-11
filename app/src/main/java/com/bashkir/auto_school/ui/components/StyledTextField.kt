package com.bashkir.auto_school.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun StyledTextField(fieldState: MutableState<TextFieldValue>, text : String, keyboardType: KeyboardType = KeyboardType.Text) =
    TextField(
        value = fieldState.value,
        onValueChange = { fieldState.value = it },
        label = { Text(text) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )