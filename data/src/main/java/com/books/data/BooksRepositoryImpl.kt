package com.books.data

import com.books.domain.abstractions.BooksRepository
import com.books.domain.entities.FirebaseResponse
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : BooksRepository {
    override suspend fun getFirebaseResponse(): FirebaseResponse {
        return remoteDataSource.getFirebaseResponse()
    }
}