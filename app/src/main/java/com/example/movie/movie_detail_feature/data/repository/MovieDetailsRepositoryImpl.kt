package com.example.movie.movie_detail_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movie.core.domain.model.Movie
import com.example.movie.core.domain.model.MovieDetails
import com.example.movie.movie_detail_feature.domain.repository.MovieDetailsRepository
import com.example.movie.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
private val remoteDataSource: MovieDetailsRemoteDataSource
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(moveId: Int): MovieDetails {
       return remoteDataSource.getMovieDetails(moveId)
    }

    override suspend fun getMoviesSimilar(
        pagingConfig: PagingConfig,
        movieId: Int
    ): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getSimilarMoviesPagingSouce(movieId)
            }
        ).flow
    }
}