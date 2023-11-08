package com.example.movie.movie_detail_feature.presentation.components

import RatingBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.movie.R
import com.example.movie.core.domain.model.Movie
import com.example.movie.core.domain.model.MovieDetails
import com.example.movie.movie_detail_feature.data.mapper.toMovie
import com.example.movie.ui.theme.black
import com.example.movie.ui.theme.white
import com.example.movie.ui.theme.yellow
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun MovieDetailContent(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetails?,
    pagingMoviesSimilar: LazyPagingItems<Movie>,
    isLoading: Boolean,
    isError: String,
    iconColor: Color,
    onAddFavorite: (Movie) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(black)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MovieDetailBackdropImage(
                backdropImageUrl = movieDetails?.backdropPathUrl.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                IconButton(
                    onClick = {
                        movieDetails?.toMovie()?.let{
                            onAddFavorite(it)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = iconColor
                    )
                }
            }
            
            Text(
                text = movieDetails?.title.toString(),
                color = white,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(
                mainAxisAlignment = MainAxisAlignment.Center,
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 10.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ){
                movieDetails?.genres?.forEach {
                    MovieDetailGenreTag(genre = it)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            MovieInfoContent(movieDetails = movieDetails, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(8.dp))

            RatingBar(
                rating = (movieDetails?.voteAvarage?.toFloat()?.div(2f)) ?: 0f,
                modifier = Modifier.height(15.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            MovieDetailOverview(
                overview = movieDetails?.overview.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = stringResource(id = R.string.movies_similar),
                color = white,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 8.dp)

            )
        }

        if(isError.isNotEmpty()){
            Text(text = isError,
                color= MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .align(Alignment.TopCenter))
        }

        if (isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                color = yellow
            )
        }

        MovieDetailSimilarMovies(
            pagingMoviesSimilar = pagingMoviesSimilar,
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.35f)
                .align(Alignment.BottomEnd)
            )

    }
}