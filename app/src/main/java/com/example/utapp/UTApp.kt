package com.example.utapp

import android.app.Application
import com.example.utapp.di.DaggerAppComponent

open class UTApp : Application() {
    val appComponent by lazy {
        DaggerAppComponent.create()
    }
}