package com.example.movie.movie_detail_feature.domain.source

import com.example.movie.core.data.remote.response.MovieResponse
import com.example.movie.core.domain.model.MovieDetails
import com.example.movie.core.paging.MovieSimilarPagingSource

interface MovieDetailsRemoteDataSource {
    suspend fun getMovieDetails(moveId: Int): MovieDetails

    suspend fun getMoviesSimilar(page: Int, movieId: Int): MovieResponse

    fun getSimilarMoviesPagingSouce(moveId: Int): MovieSimilarPagingSource
}