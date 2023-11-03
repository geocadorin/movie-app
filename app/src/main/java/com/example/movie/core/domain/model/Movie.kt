package com.example.movie.core.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val voteAvarage: Double = 0.0,
    val imageUrl: String = ""
)
