package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.MainRepository
import com.example.myapplication.viewmodel.homeviewmodel


class MyViewModelFactory constructor(private val repository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  if (modelClass.isAssignableFrom(homeviewmodel::class.java)){
            homeviewmodel(this.repository) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}