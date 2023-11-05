package com.example.movie.search_movie_feature.domain.usecase

import android.text.PrecomputedText
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movie.core.domain.model.MovieSearch
import com.example.movie.search_movie_feature.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMovieSearchUsecase{
    operator fun invoke(params: Params): Flow<PagingData<MovieSearch>>
    data class Params(val query: String)
}


class GetMovieSearchUsecaseImpl @Inject constructor(
    private val repository: MovieSearchRepository
) : GetMovieSearchUsecase {
    override fun invoke(params: GetMovieSearchUsecase.Params): Flow<PagingData<MovieSearch>> {
        return repository.getSearchMovies(
            query = params.query,
            pagingConfig = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            )
        )
    }
}