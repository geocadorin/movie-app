package com.example.movie.core.data.remote.response

import com.example.movie.core.data.remote.model.MovieResult
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    //@SerializedName é necessário para a ofuscação de código configurada no proguard
    //O nome contido no SerializedName deve ser identico a propriedade vinda da api
    @SerializedName("page")
    val page: Int,
    @SerializedName("page")
    val results: List<MovieResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)