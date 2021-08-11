package com.ramadan.reactivearch.data.repository

import com.google.android.gms.maps.model.LatLng
import com.ramadan.reactivearch.core.common.DataState
import com.ramadan.reactivearch.data.api.FoursquareAPI
import com.ramadan.reactivearch.data.cache.InMemoryCache
import com.ramadan.reactivearch.domain.dto.LocationDto
import com.ramadan.reactivearch.domain.dto.RequestDto
import com.ramadan.reactivearch.domain.entity.Restaurant
import com.ramadan.reactivearch.domain.repository.RestaurantRepository
import io.reactivex.Single

class RestaurantRepositoryImpl (private val api:FoursquareAPI) :RestaurantRepository {
    override fun getRestaurant(requestDto: RequestDto): Single<DataState<List<Restaurant>>> {
        // get cache first

        val cache = InMemoryCache.get()
        val filteredData = ArrayList<Restaurant>()
        cache.forEach{
            val latlng = LatLng(it.latitude ,it.longitude)
            if (requestDto.latLngBounds.contains(latlng))
                filteredData.add(it)
        }

        if (filteredData!=null && filteredData.isNotEmpty())
            return  Single.just(DataState.Success(filteredData))

        return  api.getRestaurants("${requestDto.latLng.latitude},${requestDto.latLng.longitude}")
            .map {
                val restList = ArrayList<Restaurant>()
                it.response.venues.forEach {
                    rest ->
                   val newRestaurant =  Restaurant(
                        id = rest.id,
                        name = rest.name,
                        city = rest.location.city,
                        address = rest.location.country,
                        latitude = rest.location.lat,
                        longitude = rest.location.lng
                    )
                    restList.add(newRestaurant)
                }
                // add data to in memory cache
                InMemoryCache.add(restList)
                DataState.Success(restList)
            }
    }
}