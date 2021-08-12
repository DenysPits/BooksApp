package com.books.domain.usecases

import com.books.domain.abstractions.BooksRepository
import com.books.domain.entities.FirebaseResponse
import javax.inject.Inject

class GetFirebaseResponse @Inject constructor(private val booksRepository: BooksRepository) {
    suspend operator fun invoke(): FirebaseResponse {
        return booksRepository.getFirebaseResponse()
    }
}