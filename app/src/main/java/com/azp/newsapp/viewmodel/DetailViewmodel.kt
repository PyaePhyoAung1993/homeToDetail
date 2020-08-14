package com.azp.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azp.newsapp.api.MovieApiClient
import com.azp.newsapp.model.Movie
import com.azp.newsapp.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewmodel : ViewModel(){


    private var result: MutableLiveData<Result> = MutableLiveData()

    fun getDetail(): LiveData<Result> = result

    fun loadDetail(id : Int) {
        Log.d("movieview",id.toString())
        var apiClient = MovieApiClient()
        val call = apiClient.getDetail(id,
            MovieApiClient.API_KEY
        )
        call.enqueue(object : Callback<Result>{
            override fun onFailure(call: Call<Result>, t: Throwable) {

            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
//                Log.d("movieresult",response.body().toString())
                    result.value = response.body()
            }

        })


} }