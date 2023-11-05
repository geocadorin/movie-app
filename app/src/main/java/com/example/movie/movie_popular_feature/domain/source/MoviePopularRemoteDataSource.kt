package com.example.movie.movie_popular_feature.domain.source

import com.example.movie.core.data.remote.response.MovieResponse
import com.example.movie.core.paging.MoviePagingSource

interface MoviePopularRemoteDataSource {

    fun getPopularMoviesPagingSource(): MoviePagingSource
    suspend fun getPopularMovies(page: Int): MovieResponse
}