package com.example.movie.movie_detail_feature.data.mapper

import com.example.movie.core.domain.model.Movie
import com.example.movie.core.domain.model.MovieDetails

fun MovieDetails.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        imageUrl = backdropPathUrl.toString(),
        voteAvarage = voteAvarage
    )
}