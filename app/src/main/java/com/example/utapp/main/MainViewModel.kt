package com.example.utapp.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import com.example.utapp.network.NetworkApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(val networkApi: NetworkApi) {

    var listOfDogs = mutableStateListOf<String>()
    var showToast = MutableLiveData<String>()

    fun start() {
        listOfDogs.addAll(
            listOf(
                "roses",
                "are",
                "red",
                "violets",
                "are",
                "blue",
                "compose",
                "ui",
                "is",
                "cool",
                "too"
            )
        )
    }

    fun tappedOn(code: Int) {
        showToast.postValue("Tapped On $code")
    }
}