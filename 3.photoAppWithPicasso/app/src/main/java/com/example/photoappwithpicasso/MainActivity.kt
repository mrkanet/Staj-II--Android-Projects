package com.example.photoappwithpicasso

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val images = arrayOf(
        R.drawable.r1, R.drawable.r2, R.drawable.r3, R.drawable.r4,
        R.drawable.r5, R.drawable.r6, R.drawable.r7, R.drawable.r8, R.drawable.r9
    )
    var lastScore = 100

    @SuppressLint("SetTextI18n")
    override fun onClick(p0: View?) {
        val indexes = arrayOf(
            images[abs(Random.nextInt() % images.size)],
            images[abs(Random.nextInt() % images.size)],
            images[abs(Random.nextInt() % images.size)]
        )

        fstImage.setImageResource(indexes[0])
        scdImage.setImageResource(indexes[1])
        thdImage.setImageResource(indexes[2])

        if (indexes[0] == indexes[1] && indexes[0] == indexes[2]) { //eğer hepsi aynıysa
            endText.text = "JACKPOT\nPuanın: $lastScore"
            btnAgain.visibility = INVISIBLE
            btnRes.visibility = VISIBLE
        } else if ((indexes[0] == indexes[1]) or (indexes[1] == indexes[2]) or (indexes[0] == indexes[2])) { // ikisi aynıysa
            endText.text = "Amorti çıktı\nKalan puan: $lastScore"
            btnAgain.text = "Tekrar"

        } else {
            lastScore--
            endText.text = "Kalan puan: $lastScore"
            btnAgain.text = "Tekrar"
        }

    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        do { //ilk başta hiçbirinin aynı olmamasını sağlıyor
            val indexes = arrayOf(
                images[abs(Random.nextInt() % images.size)],
                images[abs(Random.nextInt() % images.size)],
                images[abs(Random.nextInt() % images.size)]
            )
            lastScore = 100
            endText.text = "Puanınız: $lastScore"
            fstImage.setImageResource(indexes[0])
            scdImage.setImageResource(indexes[1])
            thdImage.setImageResource(indexes[2])
        } while (indexes[0] == indexes[1] || indexes[0] == indexes[2])
        btnAgain.setOnClickListener(this)

    }

    @SuppressLint("SetTextI18n")
    fun tryAgain(v: View) {
        do { //ilk başta hiçbirinin aynı olmamasını sağlıyor
            val indexes = arrayOf(
                images[abs(Random.nextInt() % images.size)],
                images[abs(Random.nextInt() % images.size)],
                images[abs(Random.nextInt() % images.size)]
            )
            lastScore = 100
            endText.text = "Puanınız: $lastScore"
            fstImage.setImageResource(indexes[0])
            scdImage.setImageResource(indexes[1])
            thdImage.setImageResource(indexes[2])
        } while (indexes[0] == indexes[1] || indexes[0] == indexes[2])
        btnRes.visibility = INVISIBLE
        btnAgain.visibility = VISIBLE

    }
}
