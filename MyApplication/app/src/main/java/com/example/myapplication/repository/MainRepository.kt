package com.example.myapplication.repository

import com.example.myapplication.services.marvelService

class MainRepository constructor(private val retrofitService: marvelService) {

    fun getHeroesMarvel() = retrofitService.getHeroesMvl()
}