package com.example.harfuyumu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(buttonL: View?) {
        val kalin = arrayOf('a', 'u', 'ı', 'o')
        val ince = arrayOf('e', 'ü', 'i', 'ö')
        var olcekK = 0 //pozitif varsa x>0
        var olcekI = 0 //negatif varsa x>0
        Log.d("test", editText.text.toString())

        for (_char in editText.text.toString()) {
            when (_char) {
                kalin[0] -> olcekK += 1
                kalin[1] -> olcekK += 1
                kalin[2] -> olcekK += 1
                kalin[3] -> olcekK += 1
                else -> olcekK = olcekK
            }
            when (_char) {
                ince[0] -> olcekI += 1
                ince[1] -> olcekI += 1
                ince[2] -> olcekI += 1
                ince[3] -> olcekI += 1
                else -> olcekI = olcekI
            }
        }
        if (olcekK > 0 && olcekI == 0) {
            printScreen("Kalın uyumlu")
        } else if (olcekK == 0 && olcekI > 0) {
            printScreen("İnce uyumlu")
        } else {
            printScreen("Uyumsuz")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener(this)
    }

    fun printScreen(yazi: String) {
        textView.setText("Sonuç: " + yazi)
    }


}


