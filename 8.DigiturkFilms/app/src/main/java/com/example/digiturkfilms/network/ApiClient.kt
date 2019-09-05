package com.example.digiturkfilms.network

import com.example.digiturkfilms.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{
        fun getClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

        fun getFilms():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}