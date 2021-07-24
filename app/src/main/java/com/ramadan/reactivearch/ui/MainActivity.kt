package com.ramadan.reactivearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ramadan.reactivearch.R
import com.ramadan.reactivearch.ui.feature.map.RestaurantMapFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.homeContainer,RestaurantMapFragment())
                .commit()
    }
}