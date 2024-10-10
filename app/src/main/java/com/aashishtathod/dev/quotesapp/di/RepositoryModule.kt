package com.aashishtathod.dev.quotesapp.di

import com.aashishtathod.dev.quotesapp.data.repositoryImpl.QuotesRepositoryImpl
import com.aashishtathod.dev.quotesapp.domain.repository.QuotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindQuotesRepository(quotesRepositoryImpl: QuotesRepositoryImpl): QuotesRepository
}