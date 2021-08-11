package com.ramadan.reactivearch.core.di

import com.ramadan.reactivearch.data.api.FoursquareAPI
import com.ramadan.reactivearch.data.repository.RestaurantRepositoryImpl
import com.ramadan.reactivearch.domain.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun providesRestaurantRepository(api: FoursquareAPI): RestaurantRepository {
        return RestaurantRepositoryImpl(api)
    }
}