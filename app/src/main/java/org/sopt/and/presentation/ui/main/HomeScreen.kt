package org.sopt.and.presentation.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R

@Composable
fun HomeScreen() {

    val moviePosters = listOf(
        R.drawable.film_poster_dummy,
        R.drawable.film_poster_dummy,
        R.drawable.film_poster_dummy,
        R.drawable.film_poster_dummy,
        R.drawable.film_poster_dummy,
        R.drawable.film_poster_dummy,
        R.drawable.film_poster_dummy,
        R.drawable.film_poster_dummy,
        R.drawable.film_poster_dummy
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        item {
            val categories = listOf("뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈")
            LazyRow(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(categories.size) { index ->
                    CategoryBanner(category = categories[index])
                }
            }
        }

        item {
            Text(
                text = stringResource(id = R.string.homeview_wave_editor_recommendation),
                //text = "믿고 보는 웨이브 에디터 추천작",
                modifier = Modifier
                    .padding(start = 15.dp),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600
            )
            RecommendPosterRow(moviePosters = moviePosters)
        }

        item {
            Text(
                text = stringResource(id = R.string.homeview_today_top_20),
                //text = "오늘의 TOP 20",
                modifier = Modifier
                    .padding(start = 15.dp),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600
            )
            TopPosterRow(moviePosters = moviePosters)
        }
    }
}

@Composable
fun CategoryBanner(category: String) {
    Text(
        text = category,
        color = Color.Gray,
        fontSize = 18.sp
    )
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

@Composable
fun TopPosterRow(moviePosters: List<Int>) {
    LazyRow(
        contentPadding = PaddingValues(15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(moviePosters.size) { index ->
            TopPosterItem(posterItem = moviePosters[index], rank = index + 1)
        }
    }
}

@Composable
fun TopPosterItem(posterItem: Int, rank: Int) {
    Box(
        modifier = Modifier
            .size(170.dp, 255.dp)
    ) {
        Image(
            painter = painterResource(id = posterItem),
            contentDescription = "영화 포스터",
            modifier = Modifier
                .size(150.dp, 225.dp)
                .clip(RoundedCornerShape(3.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "$rank",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 10.dp),
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight.W800,
            fontStyle = FontStyle.Italic
        )
    }
}