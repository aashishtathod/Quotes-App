package com.aashishtathod.dev.quotesapp.di

import com.aashishtathod.dev.quotesapp.data.source.remote.service.QuotesAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideQuotesApi(retrofit: Retrofit): QuotesAPI {
        return retrofit.create(QuotesAPI::class.java)
    }
}