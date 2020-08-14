package com.azp.newsapp.api

import android.util.Log
import com.azp.newsapp.model.Movie
import com.azp.newsapp.model.Result
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApiClient {

    private val BASE_URL = "https://api.themoviedb.org/3/movie/"

    companion object{
        val API_KEY = "4c78f49c86971573c1b4fda27662e6ba"
    }

    private val apiInterface: ApiInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(
            ApiInterface::class.java
        )
    }

    fun getTopHeadlines(
//        country: String,
//        category: String,
        apiKey: String
    ): Call<Movie>{
        return apiInterface.getTopHeadlines(
             apiKey
        )
    }

    fun getDetail(id : Int,apiKey: String) : Call<Result>{

        return apiInterface.getDetail(id,apiKey)
    }

}