package com.example.myapplication.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MyViewModelFactory
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.model.marvelHero
import com.example.myapplication.model.marvelHeroes
import com.example.myapplication.repository.MainRepository
import com.example.myapplication.services.marvelService
import com.example.myapplication.ui.adapter.HeroesAdapter
import com.example.myapplication.viewmodel.homeviewmodel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    lateinit var viewModel: homeviewmodel
    private val retrofitService = marvelService.getInstance()

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var heroesAdapter = HeroesAdapter()
    private var marvelHeroes : List<marvelHeroes.Data.Result> = arrayListOf()
    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding = FragmentHomeBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this,MyViewModelFactory(MainRepository(retrofitService))).get(
            homeviewmodel::class.java
        )
        //binding.recyclerMarvel.adapter = heroesAdapter
        viewModel.heroesList.observe(this, Observer {
            Log.d("TAG","onCreate: ${it}")
            heroesAdapter.setHeroesList(it)
            marvelHeroes = it
        })
        viewModel.errorMessage.observe(this, Observer {  })
        //viewModel.getHeroes()
        viewModel.getMarvelHeroes()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container, false)
        //layoutManager = LinearLayoutManager(activity)
        //binding.recyclerMarvel.layoutManager = layoutManager
        binding?.recyclerMarvel?.adapter = heroesAdapter
        binding?.recyclerMarvel?.setHasFixedSize(true)

        return binding?.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}