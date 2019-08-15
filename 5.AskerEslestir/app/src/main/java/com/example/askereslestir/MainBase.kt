package com.example.askereslestir

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

open class MainBase : AppCompatActivity() {

    fun shrPref() : SharedPreferences {
        return getSharedPreferences("com.example.askereslestir", Context.MODE_PRIVATE)
    }

}