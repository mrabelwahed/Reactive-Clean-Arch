package com.ramadan.reactivearch.data.api_response
import com.google.gson.annotations.SerializedName




data class Location (

	@SerializedName("lat") val lat : Double,
	@SerializedName("lng") val lng : Double,
	@SerializedName("labeledLatLngs") val labeledLatLngs : List<LabeledLatLngs>,
	@SerializedName("distance") val distance : Int,
	@SerializedName("cc") val cc : String,
	@SerializedName("city") val city : String,
	@SerializedName("country") val country : String,
	@SerializedName("formattedAddress") val formattedAddress : List<String>
)