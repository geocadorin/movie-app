package com.example.movie.movie_popular_feature.di

import com.example.movie.core.data.remote.MovieService
import com.example.movie.movie_popular_feature.data.repository.MoviePopularRepositoryImpl
import com.example.movie.movie_popular_feature.data.source.MoviePopularRemoteDataSourceImpl
import com.example.movie.movie_popular_feature.domain.repository.MoviePopularRepository
import com.example.movie.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import com.example.movie.movie_popular_feature.domain.usecase.GetPopularMoviesUseCase
import com.example.movie.movie_popular_feature.domain.usecase.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviePopularFeatureModule {
    @Provides
    @Singleton
    fun provideMovieDataSource(service: MovieService): MoviePopularRemoteDataSource {
        return MoviePopularRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(remoteDataSource: MoviePopularRemoteDataSource): MoviePopularRepository {
        return MoviePopularRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMoviesPopularUseCase(moviePopularRepository: MoviePopularRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(moviePopularRepository)
    }
}