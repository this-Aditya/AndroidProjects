package com.example.mygmap

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygmap.Adapters.LocationAdapter
import com.example.mygmap.Maps.DisplayMap
import com.example.mygmap.Maps.NewMap
import com.example.mygmap.data.Places
import com.example.mygmap.databinding.ActivityMainBinding
import java.io.*

var EXTRA_MAP_TITLE= "EXTRA_MAP_TITLE"
var EXTRA_MAP_DATA="EXTRA_MAP_DATA"
public const val TAG = "App"
public const val TAG2 = "App2"
private const val FILE_NAME = "Location.txt "
data class Location ( var name :String , var places : List<Places>) :Serializable
lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    var getnew = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ it->
        if (it.resultCode==Activity.RESULT_OK){
            var newlocation = it.data?.getSerializableExtra(EXTRA_MAP_DATA) as Location
            location.add(newlocation)
            serialiselocations(this,location)
            locationadapter.notifyItemInserted(location.size-1)
        }
    }



    lateinit var location: MutableList<Location>
    lateinit var locationadapter:LocationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        var data = binding.root
        super.onCreate(savedInstanceState)
        setContentView(data)
//        var locationsFromFile = desrialiselocations(this)
        location = desrialiselocations(this).toMutableList()
//        location.addAll(locationsFromFile)

        locationadapter = LocationAdapter(this,location,
        object :LocationAdapter.OnClickListened{
            override fun itemclicked(position: Int) {
                var intent = Intent(this@MainActivity,DisplayMap::class.java)
                intent.putExtra("EXTRA_LOCATION",location[position])
                startActivity(intent)
            } })
        binding.rvmain.adapter=locationadapter

        binding.rvmain.layoutManager = LinearLayoutManager(this)
    binding.fbNewMapAdd.setOnClickListener {
        showalertdialogue()
    }
    }

    override fun onPause() {
        Log.i(TAG, "onPause: Activity One ")
        super.onPause()
    }

    override fun onResume() {
        Log.i(TAG, "onResume:Main Activity ")
        super.onResume()
    }

    private fun showalertdialogue() {
var locationAddView = LayoutInflater.from(this).inflate(R.layout.addnewmapdialogue,null)
val dialogue = AlertDialog.Builder(this).
setTitle("Enter location name ").
        setView(locationAddView).
        setNegativeButton("cancel",null)
    .setPositiveButton("OK",null).show()
dialogue.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
var name = dialogue.findViewById<EditText>(R.id.etnewlocationname)?.text.toString()
    if (name.trim().isEmpty()){
        Toast.makeText(this, "This field can't be empty ", Toast.LENGTH_SHORT).show()
    dialogue.dismiss()
        return@setOnClickListener
    }
    dialogue.dismiss()
    var intent = Intent(this@MainActivity,NewMap::class.java)
    intent.putExtra(EXTRA_MAP_TITLE,name)
getnew.launch(intent)
}
    }


private fun getDataFile(context: Context):File{
    Log.i(TAG2, "getting file from directory ${context.filesDir}")
    return File(context.filesDir, FILE_NAME )
}
    private fun serialiselocations(context: Context,locationss:List<Location>){
        Log.i(TAG2, "serialiselocations: ")
        ObjectOutputStream(FileOutputStream(getDataFile(this))).use { it.writeObject(locationss) }
    }
    private fun desrialiselocations (context: Context):List<Location>{
        Log.i(TAG2, "desrialiselocations: ")
        var datafile = getDataFile(this)
        if (!datafile.exists()){
            Log.i(TAG2, "Desired data file doesnot exist ")
            return mutableListOf()
        }
        ObjectInputStream(FileInputStream(getDataFile(this))).use { return it.readObject() as List<Location> }
    }



    private fun generateSampleData(): MutableList<Location> {
        return mutableListOf(
            Location(
                "Memories from University",
                listOf(
                    Places("Branner Hall", "Best dorm at Stanford", 37.426, -122.163),
                    Places("Gates CS building", "Many long nights in this basement", 37.430, -122.173),
                    Places("Pinkberry", "First date with my wife", 37.444, -122.170)
                )
            ),
            Location("January vacation planning!",
                listOf(
                    Places("Tokyo", "Overnight layover", 35.67, 139.65),
                    Places("Ranchi", "Family visit + wedding!", 23.34, 85.31),
                    Places("Singapore", "Inspired by \"Crazy Rich Asians\"", 1.35, 103.82)
                )),
            Location("Singapore travel itinerary",
                listOf(
                    Places("Gardens by the Bay", "Amazing urban nature park", 1.282, 103.864),
                    Places("Jurong Bird Park", "Family-friendly park with many varieties of birds", 1.319, 103.706),
                    Places("Sentosa", "Island resort with panoramic views", 1.249, 103.830),
                    Places("Botanic Gardens", "One of the world's greatest tropical gardens", 1.3138, 103.8159)
                )
            ),
            Location("My favorite Places in the Midwest",
                listOf(
                    Places("Chicago", "Urban center of the midwest, the \"Windy City\"", 41.878, -87.630),
                    Places("Rochester, Michigan", "The best of Detroit suburbia", 42.681, -83.134),
                    Places("Mackinaw City", "The entrance into the Upper Peninsula", 45.777, -84.727),
                    Places("Michigan State University", "Home to the Spartans", 42.701, -84.482),
                    Places("University of Michigan", "Home to the Wolverines", 42.278, -83.738)
                )
            ),
            Location("Restaurants to try",
                listOf(
                    Places("Champ's Diner", "Retro diner in Brooklyn", 40.709, -73.941),
                    Places("Althea", "Chicago upscale dining with an amazing view", 41.895, -87.625),
                    Places("Shizen", "Elegant sushi in San Francisco", 37.768, -122.422),
                    Places("Citizen Eatery", "Bright cafe in Austin with a pink rabbit", 30.322, -97.739),
                    Places("Kati Thai", "Authentic Portland Thai food, served with love", 45.505, -122.635)
                )
            )
        )
    }
}