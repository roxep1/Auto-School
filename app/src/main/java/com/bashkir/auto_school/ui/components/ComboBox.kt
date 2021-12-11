package com.bashkir.auto_school.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

//val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
//var expanded by remember { mutableStateOf(false) }
//var selectedOptionText by remember { mutableStateOf(options[0]) }

@ExperimentalMaterialApi
@Composable
fun <T> ComboBox(
    options: List<T>,
    expanded: MutableState<Boolean>,
    selectedOption: MutableState<T?>
) =
    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = {
            expanded.value = !expanded.value
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOption.value?.toString()?:"" ,
            onValueChange = { },
            label = { Text("Label") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded.value
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {
                expanded.value = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption.value = selectionOption
                        expanded.value = false
                    }
                ) {
                    Text(text = selectionOption.toString())
                }
            }
        }
    }