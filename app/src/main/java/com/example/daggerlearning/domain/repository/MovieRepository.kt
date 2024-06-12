package com.example.daggerlearning.domain.repository

import com.example.daggerlearning.domain.model.movie.MovieList

interface MovieRepository {
    suspend fun getMovieList(): MovieList?
}