package com.books.app.di

import com.books.app.presentation.fragments.DetailsFragment
import com.books.app.presentation.fragments.MainFragment
import dagger.Component

@Component(modules = [RepositoryModule::class])
interface ApplicationComponent {

    fun inject(fragment: MainFragment)
    fun inject(fragment: DetailsFragment)
}