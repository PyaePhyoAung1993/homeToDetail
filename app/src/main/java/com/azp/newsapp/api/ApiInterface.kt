package com.azp.newsapp.api

import com.azp.newsapp.model.Movie
import com.azp.newsapp.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("top_rated")
    fun getTopHeadlines(
        @Query("api_key") apiKey: String
    ): Call<Movie>

    @GET("{movie_id}")
    fun getDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ) : Call<Result>
}