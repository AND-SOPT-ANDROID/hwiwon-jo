package org.sopt.and.presentation.ui.auth.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AuthButton(
    text: String = "",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp, horizontal = 20.dp),
        colors = buttonColors(
            backgroundColor = Color.Blue,
            contentColor = Color.White
        )
    ) {
        Text(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            text = text
        )
    }
}

@Preview
@Composable
fun PriviewAuthButton(){
    AuthButton(text = "로그인", onClick = {})
}