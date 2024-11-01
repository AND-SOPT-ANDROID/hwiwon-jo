package org.sopt.and.presentation.ui.auth.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class TextFieldValidateResult {
    object Basic : TextFieldValidateResult()
    object Error : TextFieldValidateResult()
}

@Composable
fun AuthTextField(
    modifier: Modifier = Modifier,
    validateState: TextFieldValidateResult = TextFieldValidateResult.Basic,
    placeholder: String = "",
    errorDescription: String = "",
    value: String = "",
    onValueChange: (String) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    infoIcon: ImageVector = Icons.Rounded.Info,
    infoDescription: String = ""
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray, shape = RoundedCornerShape(14.dp))
                .border(
                    width = 1.dp,
                    color = if (validateState == TextFieldValidateResult.Error) Color.Red else Color.Transparent,
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp),
                value = value,
                onValueChange = onValueChange,
                cursorBrush = SolidColor(Color.Blue),
                singleLine = true,
                visualTransformation = visualTransformation,
                decorationBox = { innerTextField ->
                    innerTextField()
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            )
            trailingIcon?.invoke()
        }
        if (errorDescription.isNotEmpty()) {
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp),
                text = when (validateState) {
                    is TextFieldValidateResult.Error -> errorDescription
                    else -> ""
                },
                color = Color.Red
            )
        }
        if (infoDescription.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (infoIcon != null) {
                    Icon(
                        imageVector = infoIcon,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Text(
                    text = infoDescription,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
    }
}