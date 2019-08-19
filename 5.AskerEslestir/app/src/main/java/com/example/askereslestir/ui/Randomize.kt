package com.example.askereslestir.ui

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.example.askereslestir.model.ListeModel
import kotlin.random.Random

@SuppressLint("Registered")
class Randomize(private val sP: SharedPreferences) : MainBase() {
    val rand = Random
    val cityList = DataSaver(sP).bringListe(DataSaver(sP).cityTag)
    val nameList = DataSaver(sP).bringListe(DataSaver(sP).nameTag)
    var shakedList: ArrayList<ListeModel> = arrayListOf()
    fun shakeIt(): ArrayList<ListeModel> {
        var savedList = cityList
        //var i = 0

        while (nameList.size > 0) {
            if (savedList.size > 0) {
                val j = rand.nextInt(nameList.size)
                val y = rand.nextInt(savedList.size)
                shakedList.add(ListeModel(savedList.get(y).text + " - " + nameList.get(j).text))
                nameList.removeAt(j)
                savedList.removeAt(y)
            } else savedList = DataSaver(sP).bringListe(DataSaver(sP).cityTag)
        }


        return shakedList
    }

}