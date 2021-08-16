package com.books.app.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.domain.entities.FirebaseResponse
import com.books.domain.usecases.GetFirebaseResponse
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseResponseViewModel @Inject constructor(getFirebaseResponse: GetFirebaseResponse) :
    ViewModel() {

    private val _firebaseResponse: MutableLiveData<FirebaseResponse> = MutableLiveData()
    val firebaseResponse: LiveData<FirebaseResponse>
        get() = _firebaseResponse

    init {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.fetchAndActivate().addOnSuccessListener {
            viewModelScope.launch(Dispatchers.IO) {
                val firebaseResponse = getFirebaseResponse()
                withContext(Dispatchers.Main) {
                    _firebaseResponse.value = firebaseResponse
                }
            }
        }
    }
}