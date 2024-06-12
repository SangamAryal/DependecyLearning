package com.example.daggerlearning.application

import android.app.Application
import com.example.daggerlearning.di.ApplicationComponent
import com.example.daggerlearning.di.DaggerApplicationComponent

class MyApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
    }

}