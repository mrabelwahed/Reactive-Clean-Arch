package com.ramadan.reactivearch.data.api_response
import com.google.gson.annotations.SerializedName



data class Response (

	@SerializedName("venues") val venues : List<Venues>
)