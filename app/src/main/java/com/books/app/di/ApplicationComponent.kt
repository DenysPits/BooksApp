package com.books.app.di

import com.books.app.di.viewmodel.ViewModelModule
import com.books.app.presentation.activities.MainActivity
import com.books.app.presentation.fragments.DetailsFragment
import com.books.app.presentation.fragments.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(fragment: MainFragment)
    fun inject(fragment: DetailsFragment)
    fun inject(activity: MainActivity)
}