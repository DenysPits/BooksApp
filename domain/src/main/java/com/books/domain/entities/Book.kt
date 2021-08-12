package com.books.domain.entities

import com.google.gson.annotations.SerializedName

data class Book(
    val id: Long,
    val name: String,
    val author: String,
    val summary: String,
    val genre: String,
    @SerializedName("cover_url") val coverUrl: String,
    val views: String,
    val likes: String,
    val quotes: String
)