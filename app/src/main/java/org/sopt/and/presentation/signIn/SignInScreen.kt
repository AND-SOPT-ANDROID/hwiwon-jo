package org.sopt.and.presentation.signIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.sopt.and.navigation.AuthNavItem
import org.sopt.and.presentation.ui.auth.component.AuthButton
import org.sopt.and.presentation.ui.auth.component.AuthServiceDescription
import org.sopt.and.presentation.ui.auth.component.AuthTextField
import org.sopt.and.presentation.ui.auth.component.ServiceIconRow
import org.sopt.and.presentation.ui.auth.component.TextFieldValidateResult

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val state by signInViewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        signInViewModel.sideEffect.collect { effect ->
            when (effect) {
                is SignInSideEffect.ShowToast -> {
                    snackbarHostState.showSnackbar(effect.message)
                }

                is SignInSideEffect.NavigateToMain -> {
                    navController.navigate(AuthNavItem.Main.route)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                text = "<",
                color = Color.White,
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "Wavve",
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .background(color = Color.Black)
                    .padding(paddingValues)
                    .padding(15.dp, 30.dp, 15.dp, 30.dp)
                    .fillMaxSize()
            ) {

                AuthTextField(
                    value = state.email,
                    onValueChange = { signInViewModel.processIntent(SignInIntent.EnterUsername(it)) },
                    placeholder = "이메일 주소 또는 아이디",
                    validateState = TextFieldValidateResult.Basic
                )
                Spacer(modifier = Modifier.height(20.dp))
                AuthTextField(
                    value = state.password,
                    onValueChange = { signInViewModel.processIntent(SignInIntent.EnterPassword(it)) },
                    placeholder = "비밀번호",
                    validateState = TextFieldValidateResult.Basic,
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        TextButton(
                            onClick = { signInViewModel.processIntent(SignInIntent.TogglePasswordVisibility) },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Text(
                                text = if (state.isPasswordVisible) "Hide" else "Show",
                                color = Color.White,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                )

                AuthButton(
                    text = if (state.isLoading) "로딩 중..." else "로그인",
                    onClick = { signInViewModel.processIntent(SignInIntent.SubmitSignIn) },
                )
                state.errorMessage?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
                AuthServiceDescription()
                ServiceIconRow()
            }
        }
    }
}



