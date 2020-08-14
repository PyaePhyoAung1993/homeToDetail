package com.azp.newsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.azp.newsapp.R
import com.azp.newsapp.viewmodel.DetailViewmodel
import com.azp.newsapp.viewmodel.MovieIdViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    lateinit var newsViewmodel: DetailViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var urlBundle = arguments?.let {
            DetailFragmentArgs.fromBundle(it)
        }
        var movieId = urlBundle?.movieId

        Log.d("movieid",movieId.toString())


        newsViewmodel = ViewModelProvider(this)
            .get(DetailViewmodel::class.java)

        movieId?.let { newsViewmodel.loadDetail(it) }
        newsViewmodel.getDetail().observe(
             viewLifecycleOwner, Observer {
               txtView.text = it.title
            }
        )

        }
    }
