package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.marvelHero
import com.example.myapplication.model.marvelHeroes
import com.example.myapplication.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class homeviewmodel constructor(private val repository : MainRepository): ViewModel() {

    val heroesList = MutableLiveData<List<marvelHeroes.Data.Result>>()
    val errorMessage = MutableLiveData<String>()


    fun getMarvelHeroes(){
        val response2 = repository.getHeroesMarvel()
        response2.enqueue(object : Callback<marvelHeroes>{
            override fun onResponse(call: Call<marvelHeroes>, response: Response<marvelHeroes>) {
                var heroesMvl = response.body()
                var hermvl = heroesMvl?.data?.results
                heroesList.postValue(hermvl!!)
            }

            override fun onFailure(call: Call<marvelHeroes>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

}