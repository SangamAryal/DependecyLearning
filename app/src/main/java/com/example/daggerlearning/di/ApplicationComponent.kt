package com.example.daggerlearning.di

import com.example.daggerlearning.ui.fragments.MainFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface ApplicationComponent {
    fun inject(fragment: MainFragment)
}