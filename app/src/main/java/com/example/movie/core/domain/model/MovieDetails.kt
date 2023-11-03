package com.example.movie.core.domain.model

import java.time.Duration

data class MovieDetails(
    val id: Int,
    val title: String,
    val genres: List<Int>,
    val overview: String?,
    val backdropPathUrl: String?,
    val releaseDate: String?,
    val voteAvarage: Double = 0.0,
    val duration: Int = 0,
    val voteCount: Int = 0
)
