package com.example.movie.search_movie_feature.data.mapper

import com.example.movie.core.data.remote.model.SearchResult
import com.example.movie.core.domain.model.MovieSearch
import com.example.movie.core.util.toPostUrl

fun List<SearchResult>.toMovieSearc() = map {
    MovieSearch(
        id = it.id,
        imageUrl = it.posterPath.toPostUrl(),
        voteAvarage = it.voteAverage
    )
}