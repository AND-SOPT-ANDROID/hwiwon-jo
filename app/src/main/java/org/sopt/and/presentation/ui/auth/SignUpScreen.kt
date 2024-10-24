package org.sopt.and.presentation.ui.auth

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.sopt.and.navigation.AuthNavItem
import org.sopt.and.presentation.utils.showToast
import org.sopt.and.presentation.viewmodel.SignUpViewModel


@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel, navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "회원가입",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "❌",
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                fontSize = 15.sp,
                color = Color.White
            )
        }

        val context = LocalContext.current
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
                value = signUpViewModel.email,
                onValueChange = { signUpViewModel.email = it },
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
                text = "⚠️ 로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해 주세요.",
                modifier = Modifier
                    .padding(15.dp),
                color = Color.Gray,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box {
                TextField(
                    value = signUpViewModel.password,
                    onValueChange = { signUpViewModel.password = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, top = 20.dp, end = 15.dp),
                    label = {
                        Text(text = "Wavve 비밀번호 설정", color = Color.DarkGray)
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Gray,
                        unfocusedContainerColor = Color.Gray
                    ),
                    placeholder = { Text("ex) abcdEFG123") },
                    visualTransformation = if (signUpViewModel.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
                )
                TextButton(
                    onClick = {
                        signUpViewModel.isPasswordVisible = !signUpViewModel.isPasswordVisible
                    },
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
                    .padding(15.dp),
                text = "⚠️ 비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용하여 입력해 주세요.",
                fontSize = 10.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = {
                    if (signUpViewModel.validateSignUp(
                            signUpViewModel.email,
                            signUpViewModel.password
                        )
                    ) {
                        signUpViewModel.setEmailAndPassword(
                            signUpViewModel.email,
                            signUpViewModel.password
                        )
                        navController.navigate(AuthNavItem.SignIn.route)
                    } else {
                        showToast(context, "회원가입 조건에 부합하지 않습니다.")
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
}
