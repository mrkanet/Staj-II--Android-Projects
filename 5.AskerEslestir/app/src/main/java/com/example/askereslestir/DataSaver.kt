package com.example.askereslestir

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.example.askereslestir.model.ListeModel

@SuppressLint("Registered")
class DataSaver(private val sP: SharedPreferences) : MainBase() {
    //private val sP = shrPref(/*Context.MODE_APPEND, "com.example.askereslestir"*/)
    val cityTag = "City"
    val nameTag = "Name"


    @SuppressLint("CommitPrefEdits")
    fun saveListe(liste: ArrayList<ListeModel>, type: String) {

        if (type == cityTag) {
            val editor = sP.edit()
            editor.putInt(cityTag, liste.size)
            for (i in 0 until liste.size) {
                editor.putString("City-$i", liste[i].text)
            }
        } else if (type == nameTag) {
            val editor = sP.edit()
            editor.putInt(nameTag, liste.size).apply()
            for (i in 0 until liste.size) {
                editor.putString("Name-$i", liste[i].text)
            }

        }
        sP.edit().apply()
    }

    fun addNext(type: String, next: String) {
        var size = sP.getInt(type,0)
        sP.edit().putString("$type-$size",next).apply()
        sP.edit().putInt(type,size+1).apply()
    }

    fun bringListe(type: String): ArrayList<ListeModel> {
        val liste = arrayListOf<ListeModel>()
        for(i in 0 until sP.getInt(type,0)){
            liste.add(ListeModel(sP.getString("$type-$i","ERROR!")))
        }
        return liste
    }

    fun eraseElement(type: String, element: String) {
        val size: Int = sP.getInt(type, 0)
        for (i in 0 until size) {
            val isIt = sP.getString("$type-$i", "ERROR!")
            if (element == isIt) {
                val editor = sP.edit()
                for (j in i until size) {
                    editor.putString("$type-$i", sP.getString("$type-${i + 1}", "ERROR!"))
                }
                editor.putInt(type, size - 1).apply()
                break
            }
        }
    }

}