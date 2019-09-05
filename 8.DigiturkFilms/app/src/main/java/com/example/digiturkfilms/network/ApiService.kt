package com.example.digiturkfilms.network

import com.example.digiturkfilms.model.GenreResultModel
import com.example.digiturkfilms.model.ResultModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/genre/movie/list?")
    fun getGenreList(
        @Query("api_key") api_key: String = "3bb3e67969473d0cb4a48a0dd61af747",
        @Query("language") language: String = "en-US"
    ): Call<GenreResultModel>


    //movie?api_key=3bb3e67969473d0cb4a48a0dd61af747&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=
    @GET("3/discover/movie?")
    fun getFilmList(
        @Query("api_key") api_key: String = "3bb3e67969473d0cb4a48a0dd61af747",
        @Query("sort_by") sort_by: String = "popularity.desc",
        @Query("include_adult") include_adult: String = "false",
        @Query("include_video") include_video: String = "false",
        @Query("page") page: Int = 1,
        @Query("with_genres") with_genres: Int
    ): Call<ResultModel>
}