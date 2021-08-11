package com.ramadan.reactivearch.data.api_response
import com.google.gson.annotations.SerializedName


data class Venues (

	@SerializedName("id") val id : String,
	@SerializedName("name") val name : String,
	@SerializedName("location") val location : Location,
	@SerializedName("categories") val categories : List<Categories>,
	@SerializedName("referralId") val referralId : String,
	@SerializedName("hasPerk") val hasPerk : Boolean
)