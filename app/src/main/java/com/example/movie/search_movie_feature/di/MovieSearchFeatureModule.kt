package com.example.movie.search_movie_feature.di

import com.example.movie.core.data.remote.MovieService
import com.example.movie.search_movie_feature.data.repository.MovieSearchRepositoryImpl
import com.example.movie.search_movie_feature.data.source.MovieSearchRemoteDataSourceImpl
import com.example.movie.search_movie_feature.domain.repository.MovieSearchRepository
import com.example.movie.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import com.example.movie.search_movie_feature.domain.usecase.GetMovieSearchUsecase
import com.example.movie.search_movie_feature.domain.usecase.GetMovieSearchUsecaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieSearchFeatureModule {
    @Provides
    @Singleton
    fun provideMovieSearchDataSource(service: MovieService): MovieSearchRemoteDataSource {
        return MovieSearchRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieSearchRepository(remoteDataSource: MovieSearchRemoteDataSource): MovieSearchRepository {
        return MovieSearchRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMoviesSearchUseCase(movieSearchRepository: MovieSearchRepository): GetMovieSearchUsecase {
        return GetMovieSearchUsecaseImpl(movieSearchRepository)
    }

}