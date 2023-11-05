package com.example.movie.movie_popular_feature.data.mapper

import com.example.movie.core.data.remote.model.MovieResult
import com.example.movie.core.domain.model.Movie
import com.example.movie.core.util.toPostUrl

fun List<MovieResult>.toMovie() = map{movieResult ->
    Movie(
        id = movieResult.id,
        title = movieResult.title,
        voteAvarage = movieResult.voteAverage,
        imageUrl = movieResult?.posterPath?.toPostUrl() ?: ""
    )
}