package com.example.movie.search_movie_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movie.search_movie_feature.presentation.components.SearchContent
import com.example.movie.search_movie_feature.presentation.state.MovieSearchState
import com.example.movie.ui.theme.black
import com.example.movie.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState,
    onEvent: (MovieSearchEvent) -> Unit,
    onFetch: (String) -> Unit,
    navigateToDetailMovie: (Int) -> Unit
) {
    val pagingMovies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black,
                    titleContentColor = white,
                ),
                title = {
                    Text("Search Movies")
                }
            )
        },
        content = { paddingValues ->
            SearchContent(
                paddingValues = paddingValues,
                paggingMovies = pagingMovies,
                query = uiState.query,
                onSearch = {
                    onFetch(it)
                },
                onEvent = {
                    onEvent(it)
                },
                onDetails = {
                    navigateToDetailMovie(it)
                }
            )

        }
    )
}