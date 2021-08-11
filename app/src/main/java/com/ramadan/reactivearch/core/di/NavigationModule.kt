package com.ramadan.reactivearch.core.di

import com.ramadan.reactivearch.core.navigation.AppNavigator
import com.ramadan.reactivearch.core.navigation.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {
    @Binds
    abstract fun bindsAppNavigator(appNavigator: AppNavigatorImpl) : AppNavigator
}