package org.sopt.and.presentation.signup

import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import org.sopt.and.presentation.ui.auth.component.AuthServiceDescription
import org.sopt.and.presentation.ui.auth.component.AuthTextField
import org.sopt.and.presentation.ui.auth.component.ServiceIconRow
import org.sopt.and.presentation.ui.auth.component.TextFieldValidateResult


@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val state by signUpViewModel.state.collectAsState()
    val sideEffect = signUpViewModel.sideEffect
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        sideEffect.collect { effect ->
            when (effect) {
                is SignUpSideEffect.ShowToast -> Toast.makeText(
                    context,
                    effect.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

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
                value = state.email,
                onValueChange = { signUpViewModel.processIntent(SignUpIntent.EnterUsername(it)) },
                placeholder = "wavve@example.com",
                validateState = TextFieldValidateResult.Basic,
                infoDescription = stringResource(R.string.signup_username_description)
            )
            Spacer(modifier = Modifier.height(10.dp))
            AuthTextField(
                value = state.password,
                onValueChange = { signUpViewModel.processIntent(SignUpIntent.EnterPassword(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, top = 20.dp, end = 15.dp),
                placeholder = "ex) abcdEFG123",
                validateState = TextFieldValidateResult.Basic,
                visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    TextButton(
                        onClick = {
                            signUpViewModel.processIntent(SignUpIntent.TogglePasswordVisibility)
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(
                            text = if (state.isPasswordVisible) "Hide" else "Show",
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
                value = state.hobby,
                onValueChange = { signUpViewModel.processIntent(SignUpIntent.EnterHobby(it)) },
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
                    signUpViewModel.processIntent(SignUpIntent.SubmitSignUp)
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