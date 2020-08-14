package com.azp.newsapp.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azp.newsapp.api.MovieApiClient
import com.azp.newsapp.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieIdViewModel: ViewModel() {

    private var result: MutableLiveData<Movie> = MutableLiveData()

    fun getResult(): LiveData<Movie> = result

    fun loadNews() {
        var apiClient = MovieApiClient()
        val call = apiClient.getTopHeadlines(
            MovieApiClient.API_KEY
        )
        call.enqueue(object: Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("Error>>>>",t.toString())
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                result.value = response.body()
            }

        })
    }
}