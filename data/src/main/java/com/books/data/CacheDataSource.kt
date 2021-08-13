package com.books.data

import com.books.domain.entities.FirebaseResponse
import javax.inject.Inject

class CacheDataSource @Inject constructor() {
    var firebaseResponse: FirebaseResponse? = null
}
