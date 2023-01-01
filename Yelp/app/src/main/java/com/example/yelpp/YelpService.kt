package com.example.yelpp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YelpService {
    @GET("businesses/search")
    fun getRestaurants(
        @Header("Authorization") authorize:String,
        @Query ("term") searchFood:String,
        @Query("location") location:String
    ):Call<YelpDatas>
}