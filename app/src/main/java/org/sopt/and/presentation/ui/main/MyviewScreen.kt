package org.sopt.and.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
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
                text = signUpViewModel.email,
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
        Box(
            modifier = Modifier
                .background(Color.Black)
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "전체 시청내역",
                color = Color.White,
                fontWeight = FontWeight.W800,
                fontSize = 23.sp
            )
            Text(
                text = "⚠️",
                fontSize = 50.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 90.dp)
            )
            Text(
                text = "시청내역이 없어요.",
                fontSize = 15.sp,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 170.dp)
            )
        }
        Box(
            modifier = Modifier
                .background(Color.Black)
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "관심 프로그램",
                color = Color.White,
                fontWeight = FontWeight.W800,
                fontSize = 23.sp
            )
            Text(
                text = "⚠️",
                fontSize = 50.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 90.dp)
            )
            Text(
                text = "관심 프로그램이 없어요.",
                fontSize = 15.sp,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 170.dp)
            )
        }
    }
}