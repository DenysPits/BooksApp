package com.books.data

import com.books.domain.entities.FirebaseResponse
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import kotlinx.coroutines.delay
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {
    private val remoteConfig = FirebaseRemoteConfig.getInstance()

    suspend fun getFirebaseResponse(): FirebaseResponse {
        var response: FirebaseResponse? = null
        remoteConfig.fetchAndActivate().addOnSuccessListener {
            val gson = Gson()
            val json = remoteConfig.getString("json_data")
            response = gson.fromJson(json, FirebaseResponse::class.java)
        }
        while (response == null) {
            delay(1)
        }
        return response!!
    }
}
