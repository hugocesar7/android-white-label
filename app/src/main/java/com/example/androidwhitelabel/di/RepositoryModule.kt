package com.example.androidwhitelabel.di

import com.example.androidwhitelabel.data.network.CountriesApi
import com.example.androidwhitelabel.data.repository.CountriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCountriesRepository(api: CountriesApi): CountriesRepository {
        return CountriesRepository(api)
    }
}