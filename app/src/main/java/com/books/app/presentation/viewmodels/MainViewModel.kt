package com.books.app.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.books.app.presentation.adapters.Genre
import com.books.domain.entities.Book
import com.books.domain.entities.FirebaseResponse
import com.books.domain.usecases.GetFirebaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainViewModel @Inject constructor(getFirebaseResponse: GetFirebaseResponse) : ViewModel() {

    lateinit var genres: List<Genre>
    val firebaseResponseLiveData: LiveData<FirebaseResponse> = liveData(Dispatchers.IO) {
        val firebaseResponse = getFirebaseResponse()
        genres = convertBooksToGenres(firebaseResponse.books)
        emit(firebaseResponse)
    }

    private var progressValue = 0
    private val timeInterval: Long = 2000 / 100

    val progress: LiveData<Int> = liveData(Dispatchers.IO) {
        while (progressValue != 101) {
            emit(progressValue)
            progressValue++
            delay(timeInterval)
        }
    }

    private fun convertBooksToGenres(books: List<Book>): List<Genre> {
        return books.groupBy { it.genre }.map { Genre(it.key, it.value) }
    }
}