package com.books.app.di

import com.books.data.BooksRepositoryImpl
import com.books.domain.abstractions.BooksRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideStorage(booksRepository: BooksRepositoryImpl): BooksRepository
}