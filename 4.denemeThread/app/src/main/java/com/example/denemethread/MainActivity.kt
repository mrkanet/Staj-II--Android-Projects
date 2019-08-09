package com.example.denemethread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val images = arrayOf(
        R.drawable.r1, R.drawable.r2, R.drawable.r3,
        R.drawable.r4, R.drawable.r5, R.drawable.r6,
        R.drawable.r7, R.drawable.r8, R.drawable.r9
    )
    val rand = Random
    var isStop = true
    var jeton = 100
    var chance = 5 // 1/5 = %20


    override fun onClick(p0: View?) {
        if (isStop) {
            isStop = false
            startTurn()
        } else isStop = true
    }

    private fun startTurn() {
        val process = Thread(Runnable {
            while (true) {
                val indexes = arrayOf(
                    rand.nextInt(images.size),
                    rand.nextInt(images.size),
                    rand.nextInt(images.size)
                )
                changeImg(indexes)
                if (isStop) {//durduruldu
                    jeton--
                    if (rand.nextInt(chance) == chance - 1) { //Jackpot tutturuldu
                        val jack = rand.nextInt(images.size)
                        setImg(jack, jack, jack)
                        chance = 20
                        jeton += 100
                        runOnUiThread {
                            pointText.text = "+100 Jeton\nJeton: $jeton"
                        }
                    } else {
                        if (indexes[0] == indexes[1] || indexes[1] == indexes[2] || indexes[0] == indexes[2]) {
                            runOnUiThread {
                                jeton++
                                pointText.text = "Amorti\n" + "Kalan Jeton: $jeton"
                            }
                        } else {
                            runOnUiThread {
                                pointText.text = "Kalan Jeton: $jeton"
                            }
                        }

                    }
                    break
                }
                Thread.sleep(20)
            }
        })
        process.start()
    }

    private fun setImg(f: Int, s: Int, t: Int) {
        runOnUiThread {
            //pointText.text = "Amorti\nJeton: $jeton"
            fstImage.setImageResource(images[f])
            scdImage.setImageResource(images[s])
            thdImage.setImageResource(images[t])
        }
    }

    private fun changeImg(indexes: Array<Int>) {

        setImg(indexes[0], indexes[1], indexes[2])

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startGame.setOnClickListener(this)
        val indexes = arrayOf(
            rand.nextInt(images.size),
            rand.nextInt(images.size),
            rand.nextInt(images.size)
        )
        setImg(indexes[0], indexes[1], indexes[2])

    }
}
