package com.ramadan.reactivearch.domain.repository

import com.ramadan.reactivearch.core.common.DataState
import com.ramadan.reactivearch.domain.dto.LocationDto
import com.ramadan.reactivearch.domain.dto.RequestDto
import com.ramadan.reactivearch.domain.entity.Restaurant
import io.reactivex.Single

interface RestaurantRepository {
    fun getRestaurant(requestDto: RequestDto) : Single<DataState<List<Restaurant>>>
}