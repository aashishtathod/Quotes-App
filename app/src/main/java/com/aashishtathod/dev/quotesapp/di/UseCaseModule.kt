package com.aashishtathod.dev.quotesapp.di

import com.aashishtathod.dev.quotesapp.domain.repository.QuotesRepository
import com.aashishtathod.dev.quotesapp.domain.usecase.GetQuoteListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetQuoteListUseCase(repository: QuotesRepository): GetQuoteListUseCase {
        return GetQuoteListUseCase(repository)
    }

}