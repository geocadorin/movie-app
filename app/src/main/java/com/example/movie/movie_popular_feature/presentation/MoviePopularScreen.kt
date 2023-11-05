package com.example.movie.movie_popular_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movie.movie_popular_feature.presentation.components.MovieContent
import com.example.movie.movie_popular_feature.presentation.state.MoviePopularState
import com.example.movie.core.util.UtilFunctions
import com.example.movie.ui.theme.black
import com.example.movie.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviePopularScreen(
    uiState: MoviePopularState,
    navigateToDetailMovie: (Int) -> Unit
) {
    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black,
                    titleContentColor = white,
                ),
                title = {
                    Text("Small Top App Bar")
                }
            )
        },
        content = { paddingValues ->
            MovieContent(
                //modifier = Modifier,
                paggingMovies = movies,
                paddingValues = paddingValues,
                onClick = {movieId ->  
                    UtilFunctions.logInfo("Movie_Id", movieId.toString())
                    navigateToDetailMovie(movieId)
                }
            )
        }

    )
}