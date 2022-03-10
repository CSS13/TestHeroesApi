package com.example.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.HeroRawBinding
import com.example.myapplication.model.marvelHero
import com.example.myapplication.model.marvelHeroes

class HeroesAdapter : RecyclerView.Adapter<heroesHolder>() {

    var heroes = mutableListOf<marvelHeroes.Data.Result>()

    fun setHeroesList(heroes : List<marvelHeroes.Data.Result>){
        this.heroes = heroes.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): heroesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HeroRawBinding.inflate(inflater,parent,false)
        return heroesHolder(binding)
    }

    override fun onBindViewHolder(holder: heroesHolder, position: Int) {
        val heroe = heroes[position]
        holder.binding.tvHeroName.text =heroe.name
        var imagepath = heroe.thumbnail?.path +"."+ heroe.thumbnail?.extension
        Glide.with(holder.itemView.context).load(imagepath).into(holder.binding.heroPhoto)
    }

    override fun getItemCount()= heroes.size
    }

class heroesHolder(val binding: HeroRawBinding): RecyclerView.ViewHolder(binding.root)