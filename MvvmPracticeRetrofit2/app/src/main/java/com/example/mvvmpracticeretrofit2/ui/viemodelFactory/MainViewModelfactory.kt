package com.example.mvvmpracticeretrofit2.ui.viemodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmpracticeretrofit2.data.repository.Repository
import com.example.mvvmpracticeretrofit2.ui.viewmodel.MainViewModel

class MainViewModelfactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}