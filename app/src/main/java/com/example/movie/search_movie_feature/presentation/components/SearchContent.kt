package com.example.movie.search_movie_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.movie.core.domain.model.MovieSearch
import com.example.movie.core.presentation.components.common.ErrorScreen
import com.example.movie.core.presentation.components.common.LoadingView
import com.example.movie.movie_popular_feature.presentation.components.MovieItem
import com.example.movie.search_movie_feature.presentation.MovieSearchEvent
import com.example.movie.ui.theme.black

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    paggingMovies: LazyPagingItems<MovieSearch>,
    query: String,
    onSearch: (String) -> Unit,
    onEvent: (MovieSearchEvent) -> Unit,
    onDetails: (movieId: Int) -> Unit,
) {

    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(top = 64.dp)
            .fillMaxSize()
            .background(black),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {

        SearchFieldComponent(
            query = query,
            onSearch = {
                isLoading = true
                onSearch(it)
            },
            onQueryChangeEvent = {
                onEvent(it)
            },
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            items(paggingMovies.itemCount) { index ->
                val movie = paggingMovies[index]
                movie?.let {
                    MovieItem(
                        voteAvareage = it.voteAvarage,
                        imageUrl = it.imageUrl,
                        id = it.id,
                        onClick = { movieId ->
                            onDetails(movieId)
                        }
                    )
                }
                isLoading = false
            }

            paggingMovies.apply {
                when {
                    isLoading -> {
                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
                            LoadingView()
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        isLoading = false
                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            }
                        )  {
                            ErrorScreen(
                                message = "Verifique sua Conexão com a Internet",
                                retry = {
                                    retry()
                                }
                            )
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        isLoading = false
                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            }
                        )  {
                            ErrorScreen(
                                message = "Verifique sua Conexão com a Internet",
                                retry = {
                                    retry()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}