package com.example.mvvmpracticeretrofit2.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmpracticeretrofit2.data.model.SearchUsers
import com.example.mvvmpracticeretrofit2.data.model.Users
import com.example.mvvmpracticeretrofit2.data.repository.Repository
import com.example.mvvmpracticeretrofit2.ui.view.MainActivity
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    var myUsers :MutableLiveData<Response<List<Users>>> = MutableLiveData()
    var mySearchUsers :MutableLiveData<Response<List<SearchUsers>>> = MutableLiveData()
    fun getUsers(){
        viewModelScope.launch {
          var response =  repository.getUsers()
            Log.i("MainActivity", "getUsers: ${response.code()} ")
            myUsers.value = response
        }
    }
    fun SearchUsers(name :String){
        viewModelScope.launch {
            var response = repository.searchUsers(name)
  mySearchUsers.value = response
        }
    }
}