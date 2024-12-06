package org.sopt.and.presentation.ui.auth

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.sopt.and.navigation.AuthNavItem
import org.sopt.and.presentation.ui.auth.component.AuthButton
import org.sopt.and.presentation.ui.auth.component.AuthServiceDescription
import org.sopt.and.presentation.ui.auth.component.AuthTextField
import org.sopt.and.presentation.ui.auth.component.ServiceIconRow
import org.sopt.and.presentation.ui.auth.component.TextFieldValidateResult
import org.sopt.and.presentation.viewmodel.SignInViewModel

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val signInResult by signInViewModel.loginResult.observeAsState()

    var isPasswordVisible by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

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
                    value = signInViewModel.username,
                    onValueChange = { signInViewModel.updateUsernameLogin(it) },
                    placeholder = "이메일 주소 또는 아이디",
                    validateState = TextFieldValidateResult.Basic
                )
                Spacer(modifier = Modifier.height(20.dp))
                AuthTextField(
                    value = signInViewModel.password,
                    onValueChange = { signInViewModel.updatePasswordLogin(it) },
                    placeholder = "비밀번호",
                    validateState = TextFieldValidateResult.Basic,
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        TextButton(
                            onClick = { isPasswordVisible = !isPasswordVisible },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Text(
                                text = if (isPasswordVisible) "Hide" else "Show",
                                color = Color.White,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                )

                AuthButton(
                    text = "로그인",
                    onClick = {
                        signInViewModel.postLogin(
                            signInViewModel.username,
                            signInViewModel.password
                        )
                    }
                )
                signInResult?.let { result ->
                    if (result.isSuccess) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("로그인 성공!")
                        }
                        navController.navigate(AuthNavItem.Main.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("로그인 실패!")
                        }
                        val errorMessage = result.exceptionOrNull()?.message ?: "로그인 실패!!"
                        Log.e("signInScreen", errorMessage)
                    }
                }
                AuthServiceDescription()
                ServiceIconRow()
            }
        }
    }
}



