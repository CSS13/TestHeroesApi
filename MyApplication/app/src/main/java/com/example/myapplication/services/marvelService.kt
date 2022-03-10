package com.example.myapplication.services

import com.example.myapplication.model.marvelHero
import com.example.myapplication.model.marvelHeroes
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface marvelService {
    @GET("v1/public/characters?ts=1&apikey=311466e4052ba5b83a2811c6e99c396b&hash=66c86be6cab94440165adccccb0c38c7")
    fun getHeroesMvl(): Call<marvelHeroes>
    companion object{
        var retrofitService : marvelService? = null

        fun getInstance(): marvelService{
            if (retrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://gateway.marvel.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(marvelService::class.java)
            }
            return retrofitService!!
        }
    }
}