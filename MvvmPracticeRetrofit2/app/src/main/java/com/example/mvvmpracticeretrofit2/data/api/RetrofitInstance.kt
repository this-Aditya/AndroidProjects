package com.example.mvvmpracticeretrofit2.data.api

import com.example.mvvmpracticeretrofit2.utils.constants.Companion.BASE_URL
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()
    }
    val api:GithubService by lazy {
        retrofit.create(GithubService::class.java)
    }
}