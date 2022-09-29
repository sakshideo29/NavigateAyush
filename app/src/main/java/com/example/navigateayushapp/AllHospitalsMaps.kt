package com.example.navigateayushapp

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager

import androidx.core.content.ContextCompat

class AllHospitalsMaps : AppCompatActivity(), OnMapReadyCallback {

    private val REQUEST_LOCATION_PERMISSION = 1
    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_hospitals_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    private fun enableMyLocation(mMap: GoogleMap) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Check if location permissions are granted and if so enable the
        // location data layer.
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION -> if (grantResults.size > 0
                && grantResults[0]
                == PackageManager.PERMISSION_GRANTED
            ) {
                enableMyLocation(mMap)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        enableMyLocation(mMap)

        val mlist: ArrayList<Hospitals>? = SaveData.Maplist
        val size=mlist?.size

        if (mlist != null) for (i in mlist.indices) {
            val hospital :Hospitals=mlist[i]
            val lat:Double=hospital.lat.toDouble()
            val long:Double=hospital.long.toDouble()
            val Name:String=hospital.hospitalName
            val location=LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(location).title(Name))

        }

        // Add a marker in Sydney and move the camera
        val Loc=LatLng(SaveData.Lat,SaveData.Lan)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Loc))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Loc, 4F))

    }
}
