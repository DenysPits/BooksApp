package com.books.app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.books.domain.usecases.GetFirebaseResponse
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DetailsViewModel @Inject constructor(getFirebaseResponse: GetFirebaseResponse) : ViewModel() {

    val firebaseResponse = liveData(Dispatchers.IO) {
        emit(getFirebaseResponse())
    }
}