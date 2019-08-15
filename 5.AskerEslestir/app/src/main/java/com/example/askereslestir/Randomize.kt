package com.example.askereslestir

import android.content.SharedPreferences
import com.example.askereslestir.model.ListeModel
import kotlin.random.Random

class Randomize(private val sP: SharedPreferences) : MainBase() {
    val rand = Random
    val cityList = DataSaver(sP).bringListe(DataSaver(sP).cityTag)
    val nameList = DataSaver(sP).bringListe(DataSaver(sP).nameTag)
    var shakedList: ArrayList<ListeModel> = arrayListOf()
    fun shakeIt(): ArrayList<ListeModel> {
        var savedList = cityList
        var i = 0
        /*while (nameList.size > 0) {
            if (i < cityList.size) {
                val j = rand.nextInt(nameList.size)
                shakedList.add(ListeModel(cityList.get(i).text + " - " + nameList.get(j).text))
                nameList.removeAt(j)
                i += 1
            } else {
                i = 0 //emin değilim
                val j = rand.nextInt(nameList.size)
                shakedList.add(ListeModel(cityList.get(i).text + " - " + nameList.get(j).text))
                nameList.removeAt(j)
                i += 1
            }
        }*/

        while (nameList.size > 0) {
            if (cityList.size > 0) {
                val j = rand.nextInt(nameList.size)
                val y = rand.nextInt(savedList.size)
                shakedList.add(ListeModel(savedList.get(y).text + " - " + nameList.get(j).text))
                nameList.removeAt(j)
                savedList.removeAt(y)
            } else savedList = cityList
        }


        return shakedList
    }

}