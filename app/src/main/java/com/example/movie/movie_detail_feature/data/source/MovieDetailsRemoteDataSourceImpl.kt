package com.example.movie.movie_detail_feature.data.source

import com.example.movie.core.data.remote.MovieService
import com.example.movie.core.data.remote.response.MovieResponse
import com.example.movie.core.domain.model.MovieDetails
import com.example.movie.core.paging.MovieSimilarPagingSource
import com.example.movie.core.util.toBackdropUrl
import com.example.movie.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieDetailsRemoteDataSource {
    override suspend fun getMovieDetails(moveId: Int): MovieDetails {
        val response = service.getMovie(moveId)
        val genres = response.genres.map { it.name }
        return MovieDetails(
            id = response.id,
            title = response.title,
            overview = response.overview,
            genres = genres,
            releaseDate = response.releaseDate,
            backdropPathUrl = response.backdropPath.toBackdropUrl(),
            voteAvarage = response.voteAverage,
            duration = response.runtime,
            voteCount = response.voteCount
        )
    }

    override suspend fun getMoviesSimilar(page: Int, movieId: Int): MovieResponse {
        return service.getSimilar(movieId= movieId, page = page)
    }

    override fun getSimilarMoviesPagingSouce(moveId: Int): MovieSimilarPagingSource {
       return MovieSimilarPagingSource(this, moveId)
    }
}