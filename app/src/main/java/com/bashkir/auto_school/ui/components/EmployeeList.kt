package com.bashkir.auto_school.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bashkir.auto_school.data.models.Teacher


@ExperimentalMaterialApi
@Composable
fun EmployeeList(
    emps: List<Teacher>,
    lastItem: @Composable LazyItemScope.() -> Unit = {},
    onClick: (Teacher) -> Unit
) = LazyColumn {
    items(emps) {
        EmployeeCard(emp = it) {
            onClick(it)
        }
    }
    item(content = lastItem)

}

@ExperimentalMaterialApi
@Composable
fun EmployeeCard(emp: Teacher, onClick: () -> Unit) =
    StyledCard(onClick = onClick) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column {
                Text(text = "${emp.userInfo}")
            }
            Column {
                Text(text = emp.positionName)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = emp.salary.toString())
            }
        }
    }