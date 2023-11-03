package com.example.movie.core.movie_popular_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movie.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviePopularRepository {
    fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>>
}