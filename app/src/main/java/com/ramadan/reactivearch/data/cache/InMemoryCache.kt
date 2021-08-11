package com.ramadan.reactivearch.data.cache

import com.ramadan.reactivearch.domain.entity.Restaurant

object InMemoryCache {
    private val cache = ArrayList<Restaurant>()

    fun get() = cache

    fun add(restaurants: List<Restaurant>) {
        cache.addAll(restaurants)
    }
}