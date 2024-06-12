package com.example.daggerlearning.data.repository

import com.example.daggerlearning.data.api.ApiService
import com.example.daggerlearning.domain.model.movie.MovieList
import com.example.daggerlearning.domain.repository.MovieRepository
import com.example.daggerlearning.utils.Constants.API_KEY
import javax.inject.Inject

class MovieDataRepository @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {
    override suspend fun getMovieList(): MovieList? {
        val response = apiService.getMovieList(API_KEY)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}