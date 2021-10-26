package com.bashkir.auto_school.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bashkir.auto_school.R
import com.bashkir.auto_school.ui.theme.Gray
import com.bashkir.auto_school.ui.theme.Green


@Composable
fun AuthScreenBody(onClickAuth: () -> Unit) {
    ConstraintLayout(modifier = Modifier
        .background(Gray)
        .fillMaxSize()) {
        val (button, image, textFields) = createRefs()

        Image(
            painterResource(id = R.drawable.car),
            contentDescription = "Car",
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                bottom.linkTo(textFields.top)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            })

        Box(
            modifier = Modifier
//                .fillMaxSize()
                .constrainAs(textFields) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
            contentAlignment = Alignment.Center
        ) {
            Column {
                val loginState = remember { mutableStateOf(TextFieldValue()) }
                val passwordState = remember { mutableStateOf(TextFieldValue()) }
                TextField(
                    value = loginState.value,
                    onValueChange = { loginState.value = it },
                    label = { Text("Login") })
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = passwordState.value, onValueChange = { passwordState.value = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
        }

        Button(
            onClick = onClickAuth,
            colors = ButtonDefaults.textButtonColors(Green),
            modifier = Modifier
                .constrainAs(button) {
                    bottom.linkTo(
                        parent.bottom,
                        margin = 16.dp
                    )
                    start.linkTo(
                        parent.start
                    )
                    end.linkTo(
                        parent.end
                    )
                }
        ) {
            Text("Авторизоваться", fontSize = 20.sp)
        }
    }
}
