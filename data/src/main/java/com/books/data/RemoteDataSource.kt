package com.books.data

import com.books.domain.entities.FirebaseResponse
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {
    private val remoteConfig = FirebaseRemoteConfig.getInstance()

    fun getFirebaseResponse(): FirebaseResponse {
        val gson = Gson()
        val json = remoteConfig.getString("json_data")
        return gson.fromJson(json, FirebaseResponse::class.java)
    }
}