package org.sopt.and.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.presentation.ui.main.component.MyPurchaseInfoCol
import org.sopt.and.presentation.viewmodel.SignUpViewModel


@Composable
fun MyviewScreen(signUpViewModel: SignUpViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.DarkGray)
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "프로필 이미지",
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.CenterStart)
            )
            Text(
                text = signUpViewModel.username,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 85.dp),
                fontSize = 10.sp,
                color = Color.White
            )

            Text(
                text = "🔔",
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 45.dp)
            )

            Text(
                text = "⚙️",
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            )

        }
        MyPurchaseInfoCol(mainText = stringResource(R.string.my_1stPurchase_description))
        MyPurchaseInfoCol(mainText = stringResource(R.string.my_NowTicket_description))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Black)
        ) {
            MyPurchaseInfoCol(title = "전체 시청내역")
            MyPurchaseInfoCol(title = "관심 프로그램")
        }
    }
}