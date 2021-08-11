package com.ramadan.reactivearch.core.navigation

import androidx.fragment.app.FragmentActivity
import com.ramadan.reactivearch.R
import com.ramadan.reactivearch.domain.entity.Restaurant
import com.ramadan.reactivearch.ui.feature.map.RestaurantMapFragment
import com.ramadan.reactivearch.ui.feature.restaurant.RestaurantDetails
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(private  val activity: FragmentActivity) : AppNavigator {

    override fun navigateTo(screen: Screen , restaurant: Restaurant?) {
        val fragment = when(screen){
            Screen.MAP -> RestaurantMapFragment()
            Screen.RESTAURANT -> RestaurantDetails.newInstance(restaurant)
        }

       activity.supportFragmentManager.beginTransaction()
           .replace(R.id.homeContainer, fragment)
           .addToBackStack(fragment.javaClass.canonicalName)
           .commit()
    }
}