package com.example.mygmap.Maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import com.example.mygmap.Location

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.mygmap.R
import com.example.mygmap.databinding.ActiviityDisplayMapBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLngBounds

class DisplayMap : AppCompatActivity(), OnMapReadyCallback {
lateinit var usermap:Location
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActiviityDisplayMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usermap = intent.getSerializableExtra("EXTRA_LOCATION") as Location

        binding = ActiviityDisplayMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var boundbuilder = LatLngBounds.builder()
       for(place in usermap.places){
           val latlang =LatLng(place.latitude,place.longitude)
           mMap.addMarker(MarkerOptions().position(latlang).title(place.title).snippet(place.description))
           boundbuilder.include(latlang)
           mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundbuilder.build(),1000,1000,0))
           mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlang,10f))
       }
    }
}