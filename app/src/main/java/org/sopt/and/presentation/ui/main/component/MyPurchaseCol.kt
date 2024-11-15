package org.sopt.and.presentation.ui.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyPurchaseInfoCol(
    mainText: String,
    actionText: String = "구매하기 >",
    backgroundColor: Color = Color.DarkGray,
    mainTextColor: Color = Color.LightGray,
    actionTextColor: Color = Color.White
) {
    Column(
        modifier = Modifier
            .background(backgroundColor)
            .padding(start = 15.dp, top = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = mainText,
            fontSize = 18.sp,
            color = mainTextColor
        )
        Text(
            text = actionText,
            fontSize = 18.sp,
            color = actionTextColor
        )
    }
}