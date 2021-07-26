package com.ramadan.reactivearch.ui.feature.map

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.system.Os.accept
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ramadan.reactivearch.R
import com.ramadan.reactivearch.core.common.BaseFragment
import permissions.dispatcher.*
import timber.log.Timber

@RuntimePermissions
class RestaurantMapFragment : BaseFragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
    private val locationSettingsScreen = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        getCurrentLocation()
    }

    private val applicationSettingsScreen  = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        getCurrentLocation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        getCurrentLocationWithPermissionCheck()
    }
    // MARK -- handle permissions

    @NeedsPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
    fun getCurrentLocation() {
        if (isLocationEnabled()) {
            getLastKnownLocation {
                // log the location
                Timber.e("available lat , long: %s,%s", it.latitude, it.longitude)
            }
        } else {
            MaterialAlertDialogBuilder(getRootActivity())
                .setTitle(getString(R.string.location_not_enabled))
                .setMessage(getString(R.string.enable_location))
                .setPositiveButton(getString(R.string.enable)) { dialog, _ ->
                    // open settings screen
                    openSettingsScreen()
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.deny)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun openSettingsScreen() {
       locationSettingsScreen.launch(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }

    @OnShowRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)
    fun OnRationalAskLocation(request: PermissionRequest) {
       MaterialAlertDialogBuilder(getRootActivity())
           .setMessage(getString(R.string.location_alert))
           .setPositiveButton(getString(R.string.accept)){
               dialog,_ ->
               request.proceed()
               dialog.dismiss()
           }
           .setNegativeButton(getString(R.string.deny)){
               dialog ,_ ->
               request.cancel()
               dialog.dismiss()
           }.show()

    }

    @OnPermissionDenied(android.Manifest.permission.ACCESS_FINE_LOCATION)
    fun OnDenyAskLocation() {
        Toast.makeText(getRootActivity() , getString(R.string.location_denied),Toast.LENGTH_SHORT).show()
    }

    @OnNeverAskAgain(android.Manifest.permission.ACCESS_FINE_LOCATION)
    fun OnNeverAskLocation() {
     val openApplicationSettings = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        openApplicationSettings.data = Uri.fromParts("package",activity?.packageName,null)
        applicationSettingsScreen.launch(openApplicationSettings)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        this.onRequestPermissionsResult(requestCode , grantResults)
    }

    // MARK end
}