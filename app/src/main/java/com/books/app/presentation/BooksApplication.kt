package com.books.app.presentation

import android.app.Application
import com.books.app.presentation.di.ApplicationComponent
import com.books.app.presentation.di.DaggerApplicationComponent

class BooksApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.create()
    }
}