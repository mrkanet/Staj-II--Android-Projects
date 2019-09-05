package com.example.retrofit.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.*
import com.example.retrofit.network.ApiService
import com.example.retrofit.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        RetrofitClient.getClient()
            .create(ApiService::class.java)
            .getRepos(
                search_input.text.toString()
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
                    rv_results.adapter = ResponseAdapter(
                        repos!!
                    ) {
                        //onclick
                        val open = Intent(Intent.ACTION_VIEW)
                        open.data = Uri.parse("https://github.com/${it.owner.login}/${it.name}")
                        startActivity(open)
                    }
                }



            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_results.layoutManager = LinearLayoutManager(this)

        search_button.setOnClickListener(this)

    }
}
