package com.example.movie.core.movie_popular_feature.data.source

import com.example.movie.core.data.remote.MovieService
import com.example.movie.core.data.remote.response.MovieResponse
import com.example.movie.core.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import com.example.movie.core.paging.MoviePagingSource

class MoviePopularRemoteDataSourceImpl constructor(
    private val service: MovieService
): MoviePopularRemoteDataSource {
    override fun getPopularMoviesPagingSource(): MoviePagingSource {
        return MoviePagingSource(this)
    }

    override suspend fun getPopularMovies(page: Int): MovieResponse {
        return service.getPopularMovies(page)
    }
}