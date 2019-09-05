package com.example.retrofit.network

import com.example.retrofit.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories")
    fun getRepos(
        @Query("q") query: String
    ): Call<ResponseModel>
}