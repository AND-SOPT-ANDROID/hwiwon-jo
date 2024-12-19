package org.sopt.and.presentation.ui.component.auth

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R

val socialIcons = listOf(
    R.drawable.ic_google,
    R.drawable.ic_apple,
    R.drawable.ic_facebook,
    R.drawable.ic_kakao,
    R.drawable.ic_naver
)

@Composable
fun AuthServiceIcon(
    @DrawableRes iconRes: Int,
    onClick: () -> Unit = {}
) {
    IconButton(onClick = onClick) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop

            )
        }
    }
}

@Composable
fun ServiceIconRow(iconList: List<Int> = socialIcons) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        iconList.forEach { iconRes ->
            AuthServiceIcon(iconRes = iconRes)
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun AuthServiceDescription() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Color.Gray)
        )
        Text(
            text = stringResource(id = R.string.sign_social_description),
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Color.Gray)
        )
    }
}