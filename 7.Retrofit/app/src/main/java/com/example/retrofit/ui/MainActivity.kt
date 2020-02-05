package com.example.retrofit.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.*
import com.example.retrofit.adapter.ResponseAdapter
import com.example.retrofit.model.ResponseModel
import com.example.retrofit.network.ApiService
import com.example.retrofit.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    @SuppressLint("DefaultLocale")
    override fun onClick(p0: View?) {
        val lang = choose_language.selectedItem.toString()
        var sortby = sort_by.selectedItem.toString()
        lateinit var search: String

        if (search_input.text.toString() != "") {
            search = if (lang == "Any") search_input.text.toString()
            else "${search_input.text}+lang:${lang}"

            if (sortby == "Best Match") {
                RetrofitClient.getClient()
                    .create(ApiService::class.java)
                    .getRepos(
                        search
                    )
                    .enqueue(object : retrofit2.Callback<ResponseModel> {
                        override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                            Log.d("Fail", t.message!!)
                        }

                        override fun onResponse(
                            call: Call<ResponseModel>,
                            response: Response<ResponseModel>
                        ) {
                            val repos = response.body()
                            rv_results.adapter =
                                ResponseAdapter(
                                    repos!!
                                ) {
                                    //onclick
                                    val open = Intent(Intent.ACTION_VIEW)
                                    open.data =
                                        Uri.parse("https://github.com/${it.owner.login}/${it.name}")
                                    startActivity(open)
                                }
                        }
                    })
            } else {
                var sortas = sort_as.selectedItem.toString()
                sortas = sortas.toLowerCase()
                sortby = sortby.toLowerCase()
                RetrofitClient.getClient()
                    .create(ApiService::class.java)
                    .getSorted(
                        query = search,
                        order = sortas,
                        sort = sortby
                    )
                    .enqueue(object : retrofit2.Callback<ResponseModel> {
                        override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                            Log.d("Fail", t.message!!)
                        }

                        override fun onResponse(
                            call: Call<ResponseModel>,
                            response: Response<ResponseModel>
                        ) {
                            val repos = response.body()
                            rv_results.adapter =
                                ResponseAdapter(
                                    repos!!
                                ) {
                                    //onclick
                                    val open = Intent(Intent.ACTION_VIEW)
                                    open.data =
                                        Uri.parse("https://github.com/${it.owner.login}/${it.name}")
                                    startActivity(open)
                                }
                        }
                    })

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_results.layoutManager = LinearLayoutManager(this)

        search_button.setOnClickListener(this)

    }
}
