package com.ramadan.reactivearch.data.api_response

import com.google.gson.annotations.SerializedName

data class RestaurantsVenues(
    @SerializedName("meta") val meta: Meta,
    @SerializedName("response") val response: Response
)
