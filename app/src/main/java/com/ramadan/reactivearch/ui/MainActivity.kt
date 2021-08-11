package com.ramadan.reactivearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ramadan.reactivearch.R
import com.ramadan.reactivearch.core.navigation.AppNavigator
import com.ramadan.reactivearch.core.navigation.Screen
import com.ramadan.reactivearch.ui.feature.map.RestaurantMapFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var appNavigator: AppNavigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null)
          appNavigator.navigateTo(Screen.MAP)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //handle backstack
        if(supportFragmentManager.backStackEntryCount == 0)
            finish()
    }
}