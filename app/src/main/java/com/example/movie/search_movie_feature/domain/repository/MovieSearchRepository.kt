package com.example.movie.search_movie_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movie.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {
    fun getSearchMovies(query: String, pagingConfig: PagingConfig): Flow<PagingData<MovieSearch>>

}