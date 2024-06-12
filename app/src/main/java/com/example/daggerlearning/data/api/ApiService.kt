package com.example.daggerlearning.data.api

import com.example.daggerlearning.domain.model.movie.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovieList(
        @Query("api_key") apiKey: String
    ): Response<MovieList>

}