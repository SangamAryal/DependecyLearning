package com.example.daggerlearning.di

import com.example.daggerlearning.data.repository.MovieDataRepository
import com.example.daggerlearning.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideMovieRepository(movieDataRepository: MovieDataRepository): MovieRepository
}