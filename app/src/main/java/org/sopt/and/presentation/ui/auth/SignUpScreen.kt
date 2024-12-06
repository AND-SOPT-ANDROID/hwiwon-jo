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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.sopt.and.R
import org.sopt.and.navigation.AuthNavItem
import org.sopt.and.presentation.ui.auth.component.AuthServiceDescription
import org.sopt.and.presentation.ui.auth.component.AuthTextField
import org.sopt.and.presentation.ui.auth.component.ServiceIconRow
import org.sopt.and.presentation.ui.auth.component.TextFieldValidateResult
import org.sopt.and.presentation.utils.showToast
import org.sopt.and.presentation.viewmodel.SignUpViewModel


@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val signUpResult by signUpViewModel.userRegistrationResult.observeAsState()
    val context = LocalContext.current

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
            Icon(
                Icons.Default.Close,
                tint = Color.White,
                contentDescription = "close",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            )
        }
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
                lineHeight = 40.sp,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(15.dp))
            AuthTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, top = 30.dp, end = 15.dp),
                value = signUpViewModel.username,
                onValueChange = { signUpViewModel.username = it },
                placeholder = "wavve@example.com",
                validateState = TextFieldValidateResult.Basic,
                infoDescription = stringResource(R.string.signup_username_description)
            )
            Spacer(modifier = Modifier.height(10.dp))
            AuthTextField(
                value = signUpViewModel.password,
                onValueChange = { signUpViewModel.password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, top = 20.dp, end = 15.dp),
                placeholder = "ex) abcdEFG123",
                validateState = TextFieldValidateResult.Basic,
                visualTransformation = if (signUpViewModel.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    TextButton(
                        onClick = {
                            signUpViewModel.isPasswordVisible = !signUpViewModel.isPasswordVisible
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(
                            text = if (signUpViewModel.isPasswordVisible) "Hide" else "Show",
                            color = Color.White,
                            fontWeight = FontWeight.Normal
                        )
                    }
                },
                infoDescription = stringResource(R.string.signup_password_description)
            )
            Spacer(modifier = Modifier.height(10.dp))
            AuthTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, top = 30.dp, end = 15.dp),
                value = signUpViewModel.hobby,
                onValueChange = { signUpViewModel.hobby = it },
                placeholder = "ex) 음악 감상",
                validateState = TextFieldValidateResult.Basic,
                infoDescription = stringResource(R.string.signup_hobby_description)
            )

            Spacer(modifier = Modifier.height(40.dp))
            AuthServiceDescription()
            ServiceIconRow()
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = {
                    if (signUpViewModel.validateSignUp(
                            signUpViewModel.username,
                            signUpViewModel.password,
                            signUpViewModel.hobby
                        )
                    ) {
                        signUpViewModel.registerUser(
                            signUpViewModel.username,
                            signUpViewModel.password,
                            signUpViewModel.hobby
                        )
                    } else {
                        context.showToast(context, "회원가입 조건에 부합하지 않습니다.")
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

            signUpResult?.let { result ->
                if (result.isSuccess) {
                    LaunchedEffect(Unit) {
                        context.showToast(context, "회원가입 성공!")
                        navController.navigate(AuthNavItem.SignIn.route)
                    }
                } else {
                    val errorMessage = result.exceptionOrNull()?.message ?: "회원가입 실패"
                    Log.e("signupscreen", errorMessage)
                    context.showToast(context, errorMessage)
                }
            }

        }
    }
}