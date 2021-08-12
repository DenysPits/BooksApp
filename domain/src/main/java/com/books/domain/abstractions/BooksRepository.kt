package com.books.domain.abstractions

import com.books.domain.entities.FirebaseResponse

interface BooksRepository {

    suspend fun getFirebaseResponse(): FirebaseResponse
}