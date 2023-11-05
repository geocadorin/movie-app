package com.example.movie.search_movie_feature.domain.source

import com.example.movie.core.data.remote.response.SearchResponse
import com.example.movie.core.paging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource

    suspend fun getSearchMovies(page: Int, query: String): SearchResponse
}