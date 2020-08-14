package com.azp.newsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.azp.newsapp.R
import com.azp.newsapp.model.Result
import com.azp.newsapp.ui.adapter.MovieAdapter
import com.azp.newsapp.viewmodel.MovieIdViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), MovieAdapter.ClickListener {

    lateinit var newsViewmodel: MovieIdViewModel
    lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewmodel = ViewModelProvider(this)
            .get(MovieIdViewModel::class.java)

        movieAdapter = MovieAdapter()
        recyclerHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }
        movieAdapter.setOnClickListener(this)
        observeViewmodel()
    }

    private fun observeViewmodel() {
        newsViewmodel.getResult().observe(
            viewLifecycleOwner, Observer { news ->
                movieAdapter.updateArticle(news.results)
            }
        )
    }

    override fun onResume() {
        super.onResume()
        newsViewmodel.loadNews()
    }

    override fun onClcik(result: Result) {
//        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
        Log.d("movie",result.id.toString())
        var action = HomeFragmentDirections
            .actionHomeFragmentToDetailFragment(result.id)
        findNavController().navigate(action)
    }

}