package com.example.askereslestir.ui

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.askereslestir.DataSaver
import com.example.askereslestir.MainBase
import com.example.askereslestir.R
import com.example.askereslestir.adapter.ListeAdapter
import com.example.askereslestir.model.ListeModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MainBase() {
    private lateinit var shared: SharedPreferences
    lateinit var dataSaver: DataSaver
    //lateinit var localType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shared = this.shrPref()
        dataSaver = DataSaver(shared)
        printPage(dataSaver.cityTag, rv_city)
        printPage(dataSaver.nameTag, rv_name)
    }

    fun cityAdd(v: View) {
        if (cityTextEnter.text.toString() != "") {
            var isThere = false
            for (listeModel in dataSaver.bringListe(dataSaver.cityTag)) {
                if (listeModel.text == cityTextEnter.text.toString()) {
                    isThere = true
                    break
                }
            }
            if (!isThere) {
                dataSaver.addNext(dataSaver.cityTag, cityTextEnter.text.toString())
                printPage(DataSaver(shared).cityTag, rv_city)
            }
            cityTextEnter.setText("")
        }
    }

    fun nameAdd(v: View) {
        if (nameTextEnter.text.toString() != "") {
            var isThere = false
            for (listeModel in dataSaver.bringListe(dataSaver.nameTag)) {
                if (listeModel.text == nameTextEnter.text.toString()) {
                    isThere = true
                    break
                }
            }
            if (!isThere) {
                dataSaver.addNext(dataSaver.nameTag, nameTextEnter.text.toString())
                printPage(DataSaver(shared).nameTag, rv_name)
            }
            nameTextEnter.setText("")
        }
    }

    fun cityRm(v: View) {
        /*DataSaver(shared).eraseElement(DataSaver(shared).cityTag, cityTextEnter.text.toString())
        printPage(dataSaver.cityTag, rv_city)
        cityTextEnter.setText("")*/
        if (shrPref().getInt(dataSaver.cityTag, 0) > 0) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Dikkat!")
            builder.setMessage("Tüm listeyi silmek istiyor musun?: ${dataSaver.cityTag}")
            builder.setNegativeButton("Evet") { dialog, id ->
                shrPref().edit().putInt(dataSaver.cityTag, 0).apply()
                printPage(dataSaver.cityTag, rv_city)
            }
            builder.show()
        }

    }

    fun nameRm(v: View) {
        /*DataSaver(shared).eraseElement(DataSaver(shared).nameTag, nameTextEnter.text.toString())
        printPage(dataSaver.nameTag, rv_name)
        nameTextEnter.setText("")*/
        if (shrPref().getInt(dataSaver.nameTag, 0) > 0) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Dikkat!")
            builder.setMessage("Tüm listeyi silmek istiyor musun?: ${dataSaver.nameTag}")
            builder.setNegativeButton("Evet") { dialog, id ->
                shrPref().edit().putInt(dataSaver.nameTag, 0).apply()
                printPage(dataSaver.nameTag, rv_name)
            }
            builder.show()
        }
    }

    fun printPage(type: String, rv: RecyclerView) {
        val xListe = DataSaver(shared).bringListe(type)
        rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ListeAdapter(xListe) {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Dikkat!")
                builder.setMessage("Silmek istiyor musun?: ${it.text.toString()}")
                builder.setNegativeButton("Evet") { dialog, id ->
                    if (retType(it.text.toString()) == dataSaver.cityTag) {
                        dataSaver.eraseElement(dataSaver.cityTag, it.text.toString())
                        printPage(dataSaver.cityTag, rv_city)
                    } else if (retType(it.text.toString()) == dataSaver.nameTag) {
                        dataSaver.eraseElement(dataSaver.nameTag, it.text.toString())
                        printPage(dataSaver.nameTag, rv_name)
                    }

                }
                builder.show()
            }
        }
    }

    private fun retType(text: String): String {
        val nameSize = shared.getInt(dataSaver.nameTag, 0)
        val citySize = shared.getInt(dataSaver.cityTag, 0)
        for (i in 0 until nameSize) {
            if (text == shared.getString("${dataSaver.nameTag}-$i", "ERROR!"))
                return dataSaver.nameTag
        }
        for (i in 0 until citySize) {
            if (text == shared.getString("${dataSaver.cityTag}-$i", "ERROR!"))
                return dataSaver.cityTag
        }
        return ""
    }

}
