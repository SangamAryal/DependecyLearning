package com.example.daggerlearning.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerlearning.domain.model.movie.MovieList
import com.example.daggerlearning.domain.usecases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<MovieList?>()
    val movies: LiveData<MovieList?>
        get() = _movies

    fun fetchMovies() {
        viewModelScope.launch {
            val movieList = getMoviesUseCase.execute()
            _movies.postValue(movieList)
        }
    }
}