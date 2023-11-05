package com.example.movie.search_movie_feature.presentation.state

import androidx.paging.PagingData
import com.example.movie.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieSearchState(
    val query: String = "",
    val movies: Flow<PagingData<MovieSearch>> = emptyFlow()

)
