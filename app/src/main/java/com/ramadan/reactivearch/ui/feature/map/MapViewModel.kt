package com.ramadan.reactivearch.ui.feature.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.Marker
import com.ramadan.reactivearch.core.common.BaseViewModel
import com.ramadan.reactivearch.core.common.DataState
import com.ramadan.reactivearch.domain.dto.RequestDto
import com.ramadan.reactivearch.domain.entity.Restaurant
import com.ramadan.reactivearch.domain.interactor.GetRestaurants
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val getRestaurants: GetRestaurants) :
    BaseViewModel() {
    private val _restaurantsState = MutableLiveData<DataState<List<Restaurant>>>()

    val restaurantsState: LiveData<DataState<List<Restaurant>>>
        get() = _restaurantsState

    val markers = HashMap<Marker, Restaurant>()

    var fragmentRecreated = false // default
    fun getRestaurants(requestDto: RequestDto) {
        if (_restaurantsState.value != null) return
        _restaurantsState.value = DataState.Loading
        getRestaurants.execute(requestDto)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { restaurants -> _restaurantsState.value = restaurants }
            .also { compositeDispoable.add(it) }
    }

    fun resetRestaurantState() {
        _restaurantsState.value = null
    }

    fun getNewRestaurants(restaurants: List<Restaurant>): ArrayList<Restaurant> {
        val markersToBeDisplayed = ArrayList<Restaurant>()
        val mainList = markers.values
        if (mainList.isNotEmpty()) {
            restaurants.forEach {
                if (!mainList.contains(it))
                    markersToBeDisplayed.add(it)
            }
        } else {
            markersToBeDisplayed.addAll(restaurants)
        }

        return markersToBeDisplayed
    }
}