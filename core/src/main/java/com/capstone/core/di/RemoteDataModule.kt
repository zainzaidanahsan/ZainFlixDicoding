package com.capstone.core.di

import com.capstone.core.data.remote.network.ApiConfig
import com.capstone.core.data.remote.network.ApiDiscoverService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideMovieApiService () : ApiDiscoverService = ApiConfig.getDiscoverApiService()
}