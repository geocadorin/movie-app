package com.example.movie.search_movie_feature.data.source

import com.example.movie.core.data.remote.MovieService
import com.example.movie.core.data.remote.response.SearchResponse
import com.example.movie.core.paging.MovieSearchPagingSource
import com.example.movie.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieSearchRemoteDataSource {
    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
       return MovieSearchPagingSource(query, this)
    }

    override suspend fun getSearchMovies(page: Int, query: String): SearchResponse {
        return service.searchMovie(page, query)
    }
}