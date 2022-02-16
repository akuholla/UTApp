package com.example.utapp.di

import com.example.utapp.main.MainActivity
import com.example.utapp.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
}