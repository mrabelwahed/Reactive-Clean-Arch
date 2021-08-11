package com.ramadan.reactivearch.core.navigation

import com.ramadan.reactivearch.domain.entity.Restaurant

interface AppNavigator {
    fun navigateTo(screen:Screen , restaurant: Restaurant? = null)
}

enum class Screen{
    MAP,
    RESTAURANT
}