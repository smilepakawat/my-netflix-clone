package com.smile.mynetflixclone.data

import retrofit2.http.GET

interface MovieAPI {
    @GET("movies")
    suspend fun getMovie(): List<MovieResponse>
}
