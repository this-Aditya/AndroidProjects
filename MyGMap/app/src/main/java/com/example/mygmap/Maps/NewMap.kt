package com.example.mygmap.Maps

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mygmap.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.mygmap.data.Places
import com.example.mygmap.databinding.ActivityNewMapBinding
import com.google.android.gms.maps.model.Marker

class NewMap : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityNewMapBinding
 var markers = mutableListOf<Marker>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
supportActionBar?.title = intent.getStringExtra(EXTRA_MAP_TITLE)
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
    mMap.setOnMapLongClickListener {
        showDetailsDialogue(it)
//        Log.i("App", "onMapReady: $title $description")
//mMap.moveCamera(CameraUpdateFactory.newLatLng(it))
    }
        mMap.setOnInfoWindowClickListener { markerToDelete->
            markers.remove(markerToDelete)
            markerToDelete.remove()

        }
        var latLng2 = LatLng(27.1470,74.8566)
        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng2))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng2,10f))
    }
//lateinit var titleplace:String
    private fun showDetailsDialogue(latLng: LatLng) {
var view = LayoutInflater.from(this).inflate(R.layout.detailsdialoguenewmap,null)
        var addmarkerDialogue = AlertDialog.Builder(this).
                setTitle("Enter Details ")
            .setView(view).
                setPositiveButton("OK",null).
                setNegativeButton("Cancel",null).
                show()
        addmarkerDialogue.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
            var titleplace = addmarkerDialogue.findViewById<EditText>(R.id.etdetailsTitle)?.text.toString()
           var  description = addmarkerDialogue.findViewById<EditText>(R.id.etdetailsDescription)?.text.toString()
        if (titleplace.trim().isEmpty()&&description.trim().isEmpty()){
            Toast.makeText(this, "These entities can't be empty ", Toast.LENGTH_SHORT).show()
        addmarkerDialogue.dismiss()
            return@setOnClickListener
        }
            var marker = mMap.addMarker(MarkerOptions().position(latLng).title(titleplace).snippet(description))
            if (marker == null){
                return@setOnClickListener
            }
            markers.add(marker)
            addmarkerDialogue.dismiss()
        }
    }

    override fun onPause() {
        Log.i(TAG, "onPause: NewMAp")
        super.onPause()
    }

    override fun onResume() {
        Log.i(TAG, "onResume: New map ")
        super.onResume()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
menuInflater.inflate(R.menu.save,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mi_save) {

            if (markers.isEmpty()) {
                Toast.makeText(this, "Markers cant be empty", Toast.LENGTH_SHORT).show()
                return true
            }
            val place = markers.map { marker ->
                Places(
                    marker.title.toString(),
                    marker.snippet.toString(),
                    marker.position.latitude,
                    marker.position.longitude
                )
            }
            var location = Location(intent.getStringExtra(EXTRA_MAP_TITLE).toString(), place)
            var data = Intent()
            data.putExtra(EXTRA_MAP_DATA, location)
            setResult(Activity.RESULT_OK, data)
            finish()
            return true}
            return super.onOptionsItemSelected(item)
        }


    }