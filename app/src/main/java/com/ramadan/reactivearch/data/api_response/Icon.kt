package com.ramadan.reactivearch.data.api_response

import com.google.gson.annotations.SerializedName



data class Icon (

	@SerializedName("prefix") val prefix : String,
	@SerializedName("suffix") val suffix : String
)