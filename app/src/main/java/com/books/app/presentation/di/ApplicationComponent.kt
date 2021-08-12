package com.books.app.presentation.di

import com.books.app.presentation.fragments.DetailsFragment
import com.books.app.presentation.fragments.MainFragment
import com.books.app.presentation.fragments.SplashFragment
import dagger.Component

@Component
interface ApplicationComponent {

    fun inject(fragment: SplashFragment)
    fun inject(fragment: MainFragment)
    fun inject(fragment: DetailsFragment)
}