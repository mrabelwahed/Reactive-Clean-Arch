package com.ramadan.reactivearch.data.api

import com.ramadan.reactivearch.data.api_response.RestaurantsVenues
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FoursquareAPI {
    @GET("v2/venues/search")
    fun getRestaurants(@Query("ll",encoded = true) ll:String) : Single<RestaurantsVenues>
}