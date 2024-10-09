package org.sopt.and

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                SignUp(application)
            }
        }
    }
}

@Composable
fun SignUpHeader() {
    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            text = "회원가입",
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Text(
            text = "❌",
            fontSize = 15.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterEnd)
        )
    }
}


@Composable
fun SignUpForm(application: Application) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
    ) {
        val text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium))
            { append("이메일과 비밀번호") }
            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraLight))
            { append("만으로\n") }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium))
            { append("Wavve를 즐길 수 ") }
            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraLight))
            { append("있어요!") }
        }
        Text(
            modifier = Modifier.padding(start = 15.dp, top = 30.dp, end = 15.dp),
            text = text,
            fontSize = 28.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 30.dp, end = 15.dp),
            value = email,
            onValueChange = { email = it },
            label = {
                Text(text = "wavve@example.com", color = Color.DarkGray)
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedContainerColor = Color.Gray
            )
        )
        Text(
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp, end = 15.dp),
            text = "⚠️ 로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해 주세요.",
            fontSize = 10.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, top = 20.dp, end = 15.dp),
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(text = "Wavve 비밀번호 설정", color = Color.DarkGray)
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Gray,
                    unfocusedContainerColor = Color.Gray
                ),
                placeholder = { Text("ex) abcdEFG123") },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
            )
            TextButton(
                onClick = { isPasswordVisible = !isPasswordVisible },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(top = 20.dp, end = 8.dp)
                    .background(Color.White.copy(alpha = 0f))
            ) {
                Text(
                    text = "show",
                    color = Color.White,
                    fontWeight = FontWeight.Normal
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp, end = 15.dp),
            text = "⚠️ 비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용하여 입력해 주세요.",
            fontSize = 10.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.weight(1f))
        TextButton(
            onClick = {
                if (validateSignUp(email = email, password = password)) {
                    val intent = Intent(application, SignInAcitivity::class.java)
                    intent.apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        putExtra("email", email)
                        putExtra("password", password)
                    }
                    application.startActivity(intent)
                } else {
                    Toast.makeText(application, "회원가입 조건에 부합하지 않습니다.", Toast.LENGTH_SHORT)
                        .show()
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(alpha = 0.5f))
                .padding(top = 15.dp, bottom = 15.dp),
        ) {
            Text(
                text = "Wavve 회원가입",
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }

}


fun validateSignUp(email: String, password: String): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    if (!emailRegex.matches(email)) {
        return false
    }
    if (password.length < 8 || password.length > 20) {
        return false
    }
    val lowerCaseRegex = Regex("[a-z]")
    val upperCaseRegex = Regex("[A-Z]")
    val digitRegex = Regex("[0-9]")
    val specialRegex = Regex("[!@#\$%^&*(),.?\\\":{}|<>]")

    val hasLowerCase = lowerCaseRegex.containsMatchIn(password)
    val hasUpperCase = upperCaseRegex.containsMatchIn(password)
    val hasDigitCase = digitRegex.containsMatchIn(password)
    val hasSpecialChar = specialRegex.containsMatchIn(password)

    val criteriaCnt = listOf(hasLowerCase, hasUpperCase, hasDigitCase, hasSpecialChar).count { it }

    return criteriaCnt >= 3
}

@Composable
fun SignUp(application: Application) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SignUpHeader()
        SignUpForm(application)
    }
}
