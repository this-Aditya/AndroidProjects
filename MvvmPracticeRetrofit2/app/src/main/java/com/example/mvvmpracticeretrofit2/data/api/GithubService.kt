package com.example.mvvmpracticeretrofit2.data.api

import com.example.mvvmpracticeretrofit2.data.model.SearchUsers
import com.example.mvvmpracticeretrofit2.data.model.Users
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
@GET("/users")
suspend fun getUsers(): Response<List<Users>>
@GET("/search/users")
suspend fun searchUser(
    @Query("q") name:String
):Response<List<SearchUsers>>
}