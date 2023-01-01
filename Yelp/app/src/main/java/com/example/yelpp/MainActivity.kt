package com.example.yelpp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yelpp.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log
private const val TAG = "App"
private const val BASE_URI = "https://api.yelp.com/v3/"
private const val API_KEY = "k-3S9qkBXG-ILMXyIuPbo3aWilsAiLCA-nFE9W_IHEFefW62KfYRpk6VBEGkdnWTn5thcQNeQxMUcvm02W_DSz4xR2Q9lgNGwZZjrM6Fm3Ph824sacaWQDZORYydY3Yx"
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        var data = binding.root
        setContentView(data)
        var restraunts = mutableListOf<YelpRestaraunts>()
        binding.rvmain.adapter = RestaurantAdapter(this,restraunts,
        object :RestaurantAdapter.goToSite{
            override fun gotosite(position:Int) {
                var intent = Intent(Intent.ACTION_VIEW,
Uri.parse(restraunts[position].url)
                      )
                startActivity(intent)
            }

        }
            )
        var adapter = binding.rvmain.adapter
        binding.rvmain.layoutManager = LinearLayoutManager(this)

var retrofit = Retrofit.Builder().baseUrl(BASE_URI).addConverterFactory(GsonConverterFactory.create()).build()
val yelpService = retrofit.create(YelpService::class.java).getRestaurants("Bearer $API_KEY","Brownie",
    "Seattle, WA, United States").enqueue(object :Callback<YelpDatas>{
    override fun onResponse(call: Call<YelpDatas>, response: Response<YelpDatas>) {
        Log.i(TAG, "Response recieved $response ")
        var body = response.body()
        if (body == null){
            Log.i(TAG, "Some error on recieveing api callback ")
            return
        }
        restraunts.addAll(body.restaraunts)
        adapter?.notifyDataSetChanged()
    }

    override fun onFailure(call: Call<YelpDatas>, t: Throwable) {
        Log.i(TAG, "response is not recieved $t ")
    }

})
        supportActionBar?.title= "Brownies in Seattle"
    }}