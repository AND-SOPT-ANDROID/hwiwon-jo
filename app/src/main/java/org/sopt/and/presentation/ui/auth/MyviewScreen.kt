package org.sopt.and.presentation.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.presentation.viewmodel.MyviewViewModel


@Composable
fun MyviewScreen(myviewViewModel: MyviewViewModel = viewModel()) {
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
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "프로필 이미지",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterStart)
            )
            Text(
                text = myviewViewModel.email,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 85.dp),
                fontSize = 15.sp,
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
        Column(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(top = 30.dp, start = 15.dp, bottom = 30.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "첫 결제 시 첫 달 100원!",
                fontSize = 18.sp,
                color = Color.LightGray
            )
            Text(
                text = "구매하기 >",
                fontSize = 18.sp,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(top = 30.dp, start = 15.dp, bottom = 30.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "현재 보유하신 이용권이 없습니다.",
                fontSize = 18.sp,
                color = Color.LightGray
            )
            Text(
                text = "구매하기 >",
                fontSize = 18.sp,
                color = Color.White
            )
        }
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