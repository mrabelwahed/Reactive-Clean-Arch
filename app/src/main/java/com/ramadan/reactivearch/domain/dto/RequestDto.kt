package com.ramadan.reactivearch.domain.dto

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

data class RequestDto (val latLng: LatLng , val latLngBounds: LatLngBounds)