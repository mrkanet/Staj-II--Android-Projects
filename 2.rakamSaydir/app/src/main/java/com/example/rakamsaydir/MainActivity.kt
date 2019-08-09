package com.example.rakamsaydir

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs
import kotlin.random.Random
import android.view.View.VISIBLE as VISIBLE1

class MainActivity : AppCompatActivity(), OnClickListener {
    //private var rakamArray : MutableList<Int> = mutableListOf<Int>()
    private var rakamArray = ""
    private var point = 0
    override fun onClick(p0: View?) { //hesaplamaların olduğu fonksiyon

        with(rakamArray){
            when(this){
                rakamEnter.text.toString() -> {
                    point += 1
                    //val nextValue = abs(Random.nextInt() % 10)
                    rakamPrint.text = abs(Random.nextInt()%10).toString()
                    rakamArray += rakamPrint.text
                }
                else -> mistake()
            }
        }

       /*with(rakamArray) {
            when (size) {
                rakamEnter.text.toString().length -> {
                    var check =
                        true; for (it in 0 until size) if (this[it] != rakamEnter.text.toString()[it].toString().toInt()) {
                        check = false; break
                    }; when (check) {
                        false -> mistake()
                        else -> {
                            point += 1; add(abs(Random.nextInt() % 10)); rakamPrint.text = last().toString()
                        }
                    }
                }
                else -> mistake()
            }
        }*/
        /*
        if (rakamArray.size == rakamEnter.text.toString().length) {
            var check = true
            for (it in 0 until rakamArray.size) {
                if (rakamArray[it] != rakamEnter.text.toString()[it].toString().toInt()){
                    check = false
                    break
                }
            }
            if (!check) mistake()
            else {
                point += 1
                rakamArray.add(abs(Random.nextInt() % 10))
                rakamPrint.text = rakamArray.last().toString()
            }
        }else mistake()*/
        rakamEnter.setText("")
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //rakamArray.add(abs(Random.nextInt() % 10))
        rakamArray+=abs(Random.nextInt() % 10)
        //rakamPrint.text = "" + rakamArray[0]
        rakamPrint.text = rakamArray
        buttonCheck.setOnClickListener(this)

    }

    @SuppressLint("SetTextI18n")
    private fun mistake() { //if mistake method
        setVision(rakamEnter, buttonCheck, buttonRestart, buttonEnd)
        rakamPrint.text = "Sonuç: $point"
    }

    fun btnRestart(v: View) { //restart method
        Companion.setVision(buttonRestart, buttonEnd, rakamEnter, buttonCheck)
        point = 0
        //rakamArray.clear()
        //rakamArray.add(abs(Random.nextInt() % 10))
        rakamArray = ""
        rakamArray += abs(Random.nextInt() % 10)
        rakamPrint.text = rakamArray
    }

    private fun btnEnd(v:View) {
        finish()
    } //ender method

    companion object {
        private fun setVision(v1: View, v2: View, iv1: View, iv2: View) { //vision changer
            v1.visibility = INVISIBLE
            v2.visibility = INVISIBLE
            iv1.visibility = VISIBLE1
            iv2.visibility = VISIBLE1
        }
    }

}
