package com.example.mvvmpracticeretrofit2.data.repository

import com.example.mvvmpracticeretrofit2.data.api.RetrofitInstance
import com.example.mvvmpracticeretrofit2.data.model.Users
import retrofit2.Response

class Repository {
suspend fun getUsers():Response<List<Users>> = RetrofitInstance.api.getUsers()
suspend fun searchUsers(name:String) = RetrofitInstance.api.searchUser(name)
}