package com.bashkir.auto_school.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.auto_school.R
import com.bashkir.auto_school.ui.components.StyledTextField
import com.bashkir.auto_school.ui.theme.Gray
import com.bashkir.auto_school.ui.theme.Green
import com.bashkir.auto_school.viewmodels.AuthState
import com.bashkir.auto_school.viewmodels.AuthViewModel

@ExperimentalMaterialApi
@Composable
fun AuthScreenBody(viewModel: AuthViewModel, navigate: (Class<*>) -> Unit) =
    ConstraintLayout(
        modifier = Modifier
            .background(Gray)
            .fillMaxSize()
    ) {
        val (button, image, textFields) = createRefs()
        val error = remember { mutableStateOf(false) }

        Logo(image, textFields)

        val loginState = remember { mutableStateOf(TextFieldValue()) }
        val passwordState = remember { mutableStateOf(TextFieldValue()) }

        TextFields(textFields, loginState, passwordState, error)

        AuthButton(button, viewModel) {
            viewModel.login(loginState.value.text, passwordState.value.text, navigate)
        }
        val response by viewModel.collectAsState(AuthState::loginResponse)
        error.value = response is Fail
    }


@Composable
private fun ConstraintLayoutScope.TextFields(
    textFields: ConstrainedLayoutReference,
    loginState: MutableState<TextFieldValue>,
    passwordState: MutableState<TextFieldValue>,
    error: MutableState<Boolean>
) =
    Box(
        modifier = Modifier
            .constrainAs(textFields) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            },
        contentAlignment = Alignment.Center
    ) {
        Column {
            StyledTextField(loginState, "Login")
            Spacer(modifier = Modifier.height(16.dp))
            PasswordField(passwordState)
            if (error.value)
                Text(text = "???????????????? ?????????? ?????? ????????????", color = Color.Red)
        }
    }

@Composable
private fun ConstraintLayoutScope.Logo(
    image: ConstrainedLayoutReference,
    textFields: ConstrainedLayoutReference
) =
    Image(
        painterResource(id = R.drawable.car),
        contentDescription = "Car",
        modifier = Modifier.constrainAs(image) {
            top.linkTo(parent.top)
            bottom.linkTo(textFields.top)
            end.linkTo(parent.end)
            start.linkTo(parent.start)
        })

@Composable
private fun PasswordField(passwordState: MutableState<TextFieldValue>) =
    TextField(
        value = passwordState.value, onValueChange = { passwordState.value = it },
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )

@ExperimentalMaterialApi
@Composable
private fun ConstraintLayoutScope.AuthButton(
    button: ConstrainedLayoutReference,
    viewModel: AuthViewModel,
    onClick: () -> Unit
) {
    val enabledState = remember { mutableStateOf(true) }
    val token = viewModel.collectAsState(AuthState::loginResponse)
    enabledState.value = token.value !is Loading
    Button(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(Green),
        enabled = enabledState.value,
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
        Text("????????????????????????????", fontSize = 20.sp)
    }
}