package com.example.movie.movie_popular_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movie.R

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    voteAvareage: Double,
    imageUrl: String,
    id: Int,
    onClick: (id: Int) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        MovieRate(
            rate = voteAvareage,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(2f)
                .padding(start = 8.dp, bottom = 8.dp)
                .background(Color.Black)
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
                .padding(4.dp)
                .clickable {},
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),

            ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .error(R.drawable.ic_error_image)
                        .placeholder(R.drawable.ic_placeholder)
                        .build(),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(Color.Black)
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = null
                )
            }
        }
    }
}


@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(
        voteAvareage = 7.6,
        imageUrl = "",
        id = 1,
        onClick = {}
    )
}














