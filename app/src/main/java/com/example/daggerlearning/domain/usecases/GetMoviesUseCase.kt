package com.example.daggerlearning.domain.usecases

import com.example.daggerlearning.domain.model.movie.MovieList
import com.example.daggerlearning.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(): MovieList? {
        return movieRepository.getMovieList()
    }
}