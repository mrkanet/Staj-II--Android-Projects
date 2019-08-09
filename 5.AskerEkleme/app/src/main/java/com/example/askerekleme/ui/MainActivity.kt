package com.example.askerekleme.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.askerekleme.GenerateData
import com.example.askerekleme.R
import com.example.askerekleme.adapter.CheckBoxAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //linearLayoutManager = LinearLayoutManager(this)
       // recyclerView.layoutManager = linearLayoutManager
        //recyclerView.setHasFixedSize(true) // Boyunu sabitler

        rvCityNames.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CheckBoxAdapter(GenerateData.generateData(), {
            })
        }


    }
}
