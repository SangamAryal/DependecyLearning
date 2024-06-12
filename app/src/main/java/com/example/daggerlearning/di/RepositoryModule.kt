package com.example.daggerlearning.di

import com.example.daggerlearning.data.repository.MovieDataRepository
import com.example.daggerlearning.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideMovieRepository(movieDataRepository: MovieDataRepository): MovieRepository
}