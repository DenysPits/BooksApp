package com.books.app.presentation

import android.app.Application
import com.books.app.di.ApplicationComponent
import com.books.app.di.DaggerApplicationComponent

class BooksApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.create()
    }
}