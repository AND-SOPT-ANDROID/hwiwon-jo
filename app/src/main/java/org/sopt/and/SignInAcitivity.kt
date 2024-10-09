package org.sopt.and

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import kotlinx.coroutines.launch
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignInAcitivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val email = intent.getStringExtra("email")
                val password = intent.getStringExtra("password")
                if (email != null && password != null) {
                    Login(email, password)
                }
            }
        }
    }
}

@Composable
fun LoginHeader() {
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
}

@Composable
fun LoginForm(email: String, password: String) {
    var emailLogin by remember { mutableStateOf("") }
    var passwordLogin by remember { mutableStateOf("") }
    var isPasswowrdVisible by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(color = Color.Black)
                .padding(paddingValues)
                .padding(15.dp, 30.dp, 15.dp, 30.dp)
                .fillMaxSize()
        ) {
            TextField(
                value = emailLogin,
                onValueChange = { emailLogin = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("이메일 주소 또는 아이디") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Gray,
                    unfocusedContainerColor = Color.Gray
                )
            )

            Box() {
                TextField(
                    value = passwordLogin,
                    onValueChange = { passwordLogin = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    label = { Text("비밀번호") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Gray,
                        unfocusedContainerColor = Color.Gray
                    ),
                    visualTransformation = if (isPasswowrdVisible) VisualTransformation.None else PasswordVisualTransformation()
                )
                TextButton(
                    onClick = { isPasswowrdVisible = !isPasswowrdVisible },
                    modifier = Modifier
                        .padding(top = 20.dp, end = 8.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    Text(
                        text = "show",
                        color = Color.White,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

            Button(
                onClick = {
                    if (validateSignIn(email, password, emailLogin, passwordLogin)) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("로그인 성공!")
                        }
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("로그인 실패!")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp),
                    text = "로그인"
                )
            }
        }
    }


}

fun validateSignIn(
    email: String,
    password: String,
    emailLogin: String,
    passwordLogin: String
): Boolean {
    if (email.equals(emailLogin) && password.equals(passwordLogin)) {
        return true
    } else {
        return false
    }
}

@Composable
fun Login(email: String, password: String) {
    Column(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
    ) {
        LoginHeader()
        LoginForm(email, password)
    }
}



