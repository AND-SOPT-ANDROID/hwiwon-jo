package org.sopt.and.presentation.ui.component.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeContentList(
    title: String,
    moviePosters: List<Int>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(start = 15.dp),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.W600
        )
        RecommendPosterRow(moviePosters = moviePosters)
    }
}

@Composable
fun RecommendPosterRow(moviePosters: List<Int>) {
    LazyRow(
        contentPadding = PaddingValues(15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(moviePosters.size) { index ->
            RecommendPosterItem(posterItem = moviePosters[index])
        }
    }
}

@Composable
fun RecommendPosterItem(posterItem: Int) {
    Image(
        painter = painterResource(id = posterItem),
        contentDescription = "영화 포스터",
        modifier = Modifier
            .size(120.dp, 180.dp)
            .clip(RoundedCornerShape(3.dp)),
        contentScale = ContentScale.Crop
    )
}