package com.example.yelpp

import com.google.gson.annotations.SerializedName

data class YelpDatas(
        var total :Int,
        @SerializedName("businesses") var restaraunts: List<YelpRestaraunts>
)

data class YelpRestaraunts(
    var name :String,
    var rating :Double,
    var price :String,
    var image_url:String,
    var categories :List<Category>,
    val url :String,
    @SerializedName("location") var locationdata:LocationData
    )

data class LocationData (
    var city:String,
    @SerializedName ("address1") var adderss:String
        )

data class Category(
    var title:String
)
