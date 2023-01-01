package com.example.mvvmpracticeretrofit2.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmpracticeretrofit2.data.model.Users
import com.example.mvvmpracticeretrofit2.data.repository.Repository
import com.example.mvvmpracticeretrofit2.databinding.ActivityMainBinding
import com.example.mvvmpracticeretrofit2.ui.adapter.GithubAdapter
import com.example.mvvmpracticeretrofit2.ui.viemodelFactory.MainViewModelfactory
import com.example.mvvmpracticeretrofit2.ui.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding

     lateinit var users :MutableList<Users>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var repository=Repository()
        var viewmodelFactory = MainViewModelfactory(repository)
        var viewmodel = ViewModelProvider(this,viewmodelFactory).get(MainViewModel::class.java)
        viewmodel.getUsers()
        Log.i(TAG, "Processing")
        viewmodel.myUsers.observe(this, Observer {
            Log.i(TAG, "onCreate: ")
            if (it.isSuccessful){
                users = it.body() as MutableList<Users>
            binding.rvMain.adapter = GithubAdapter(this,users)
                binding.rvMain.layoutManager=LinearLayoutManager(this)
            }
            else{
                Log.i(TAG, "failed:(")
            }
        })
//var searchview = binding.svMain
//        searchview.isSubmitButtonEnabled = true
//       searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
//           override fun onQueryTextSubmit(query: String?): Boolean {
//               query.let {  findUsers(query)}
//               return true
//
//           }
//
//           private fun findUsers(query: String?) {
//
//               viewmodel.SearchUsers(query!!)
//               viewmodel.mySearchUsers.observe(this@MainActivity, Observer {
//                   if (it.isSuccessful){
//                       users.clear()
//                       users =it.body() as MutableList<Users>
//                       binding.rvMain.adapter?.notifyDataSetChanged()
//
//                   }
//               })
//
//           }
//
//           override fun onQueryTextChange(p0: String?): Boolean {
//return true          }
//
//       })
    }


}