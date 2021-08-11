package com.ramadan.reactivearch.data.api_response
import com.google.gson.annotations.SerializedName

data class LabeledLatLngs (

	@SerializedName("label") val label : String,
	@SerializedName("lat") val lat : Double,
	@SerializedName("lng") val lng : Double
)