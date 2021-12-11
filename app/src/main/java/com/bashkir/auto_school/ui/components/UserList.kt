package com.bashkir.auto_school.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bashkir.auto_school.data.models.User
import com.bashkir.auto_school.ui.theme.Green

@ExperimentalMaterialApi
@Composable
fun UserList(
    users: List<User> = listOf(),
    lastItem: @Composable (LazyItemScope.() -> Unit) = {}
) = LazyColumn(
    Modifier.fillMaxSize()
) {
    items(users) {
        UserCard(user = it)
    }

    item(content = lastItem)
}


@ExperimentalMaterialApi
@Composable
fun UserCard(user: User) = StyledCard {
    Row(
        modifier = Modifier.padding(10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(user.toString())
    }
}

