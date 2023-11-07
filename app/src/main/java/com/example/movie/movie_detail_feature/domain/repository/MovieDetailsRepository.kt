package com.example.movie.movie_detail_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movie.core.domain.model.Movie
import com.example.movie.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieDetails(moveId: Int): MovieDetails

    suspend fun getMoviesSimilar(pagingConfig: PagingConfig, movieId: Int): Flow<PagingData<Movie>>
}