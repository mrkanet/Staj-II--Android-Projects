package com.example.digiturkfilms.network

import com.example.digiturkfilms.model.GenreResultModel
import com.example.digiturkfilms.model.ResultModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SendRequest {

    inline fun getFilmList(
        crossinline onResponse: (Call<GenreResultModel>, Response<GenreResultModel>) -> Unit,
        crossinline onFailure: (Call<GenreResultModel>, Throwable) -> Unit
    ){
        ApiClient.getClient().create(ApiService::class.java)
            .getGenreList()
            .enqueue(object : Callback<GenreResultModel> {
                override fun onResponse(
                    call: Call<GenreResultModel>,
                    response: Response<GenreResultModel>
                ) {
                    onResponse(call, response)
                }

                override fun onFailure(call: Call<GenreResultModel>, t: Throwable) {
                    onFailure(call, t)
                }

            })
    }

    inline fun getGenreList(
        id: Int,
        crossinline onResponse: (Call<ResultModel>, Response<ResultModel>) -> Unit,
        crossinline onFailure: (Call<ResultModel>, Throwable) -> Unit
    ) {
        ApiClient.getClient()
            .create(ApiService::class.java)
            .getFilmList(with_genres = id)
            .enqueue(object : Callback<ResultModel> {
                override fun onFailure(call: Call<ResultModel>, t: Throwable) {
                    onFailure(call, t)
                }

                override fun onResponse(
                    call: Call<ResultModel>,
                    response: Response<ResultModel>
                ) {
                    onResponse(call, response)
                }
            })
    }
}