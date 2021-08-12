package com.books.app.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    private var progressValue = 0

    private val timeInterval: Long = 2000 / 100

    val progress: LiveData<Int> = liveData(Dispatchers.IO) {
        while (progressValue != 101) {
            emit(progressValue)
            progressValue++
            delay(timeInterval)
        }
    }
}