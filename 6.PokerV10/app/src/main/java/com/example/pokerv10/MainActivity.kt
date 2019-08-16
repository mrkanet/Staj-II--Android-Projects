package com.example.pokerv10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val rand = Random
    //tüm oyuncular sonradan set edilmek üzere global tanımlanır
    lateinit var players: ArrayList<ArrayList<ArrayList<Int>>>
    lateinit var cardsBack: ArrayList<ArrayList<Int>>
    var playCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //tek bir kartı set edip döndürür
    private fun setData(number: Int, type: Int): ArrayList<Int> {
        return arrayListOf<Int>(number, type)
    }

    //bütün kartları sırayla set eder ve döndürür
    private fun setAllCards(): ArrayList<ArrayList<Int>> {
        val x = arrayListOf<ArrayList<Int>>()
        for (i in 0 until 13) {
            for (j in 0 until 4) {
                x.add(setData(i, j))
            }
        }
        return x
    }

    //button click
    fun mixAll(v: View) {
        if (playCount == 0) {
            val x = chooseCards(mixCards(setAllCards()))
            playersSet(x)
            cardsText.text = calculate(players[0])
            player2.text = calculate(players[1])
            player3.text = calculate(players[2])
            player4.text = calculate(players[3])
            setLastCards(x)
            setAllImages(players[0])
            playCount++
        }else if(playCount == 1){
            playersSet(cardsBack)
            cardsText.text = calculate(players[0])
            player2.text = calculate(players[1])
            player3.text = calculate(players[2])
            player4.text = calculate(players[3])
            playCount++
        }else{
            val builder = android.app.AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Kartlar bitti")
            builder.setMessage("Yeni deste istiyor musunuz?")
            builder.setNegativeButton("Hayır") { dialog, id ->
                //resultText.text = point.toString()
                button.visibility = View.INVISIBLE
            }
            builder.setPositiveButton("Evet") { dialog, id ->
                playCount = 0
            }
            builder.show()
        }
    }

    fun setLastCards(x: ArrayList<ArrayList<Int>>){
        cardsBack = setAllCards()
        for(i in x){
            cardsBack.remove(i)
        }

    }

    //oyuncular gelen liste ile set edilir
    fun playersSet(x: ArrayList<ArrayList<Int>>) {
        players = arrayListOf()
        for (i in 0 until 4) {
            players.add(arrayListOf())
        }
        for (i in 0 until 20) {
            players[i % 4].add(x[i])
        }
    }

    //images set edilir
    fun setAllImages(x: ArrayList<ArrayList<Int>>) {
        val resources = arrayListOf(
            arrayListOf(
                R.drawable.ha,
                R.drawable.h2,
                R.drawable.h3,
                R.drawable.h4,
                R.drawable.h5,
                R.drawable.h6,
                R.drawable.h7,
                R.drawable.h8,
                R.drawable.h9,
                R.drawable.h10,
                R.drawable.hj,
                R.drawable.hq,
                R.drawable.hk
            ),
            arrayListOf(
                R.drawable.sa,
                R.drawable.s2,
                R.drawable.s3,
                R.drawable.s4,
                R.drawable.s5,
                R.drawable.s6,
                R.drawable.s7,
                R.drawable.s8,
                R.drawable.s9,
                R.drawable.s10,
                R.drawable.sj,
                R.drawable.sq,
                R.drawable.sk
            ),
            arrayListOf(
                R.drawable.ca,
                R.drawable.c2,
                R.drawable.c3,
                R.drawable.c4,
                R.drawable.c5,
                R.drawable.c6,
                R.drawable.c7,
                R.drawable.c8,
                R.drawable.c9,
                R.drawable.c10,
                R.drawable.cj,
                R.drawable.cq,
                R.drawable.ck
            ),
            arrayListOf(
                R.drawable.da,
                R.drawable.d2,
                R.drawable.d3,
                R.drawable.d4,
                R.drawable.d5,
                R.drawable.d6,
                R.drawable.d7,
                R.drawable.d8,
                R.drawable.d9,
                R.drawable.d10,
                R.drawable.dj,
                R.drawable.dq,
                R.drawable.dk
            )
        )
        imageView.setImageResource(resources[x[0][1]][x[0][0]])
        imageView1.setImageResource(resources[x[1][1]][x[1][0]])
        imageView2.setImageResource(resources[x[2][1]][x[2][0]])
        imageView3.setImageResource(resources[x[3][1]][x[3][0]])
        imageView4.setImageResource(resources[x[4][1]][x[4][0]])
    }

    //kartları karıştırma fonksiyonu
    private fun mixCards(cards: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>> {
        val mixedCards = arrayListOf<ArrayList<Int>>()
        while (cards.size > 0) {
            val x = rand.nextInt(cards.size)
            mixedCards.add(cards[x])
            cards.removeAt(x)
        }
        return mixedCards
    }

    //kartların seçim işlemi burada yapılır
    private fun chooseCards(cards: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>> {
        val choosenCards = arrayListOf<ArrayList<Int>>()
        val x = cards.size
        while (cards.size > x - 20) {
            val y = rand.nextInt(cards.size)
            choosenCards.add(cards[y])
            cards.removeAt(y)
        }
        return choosenCards
    }

    //sonuçların hesaplandığı fonksiyon
    private fun calculate(player: ArrayList<ArrayList<Int>>): String {
        val text = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        for (i in player) {
            when (i[0]) {
                1 -> text[0] = text[0] + 1
                2 -> text[1] = text[1] + 1
                3 -> text[2] = text[2] + 1
                4 -> text[3] = text[3] + 1
                5 -> text[4] = text[4] + 1
                6 -> text[5] = text[5] + 1
                7 -> text[6] = text[6] + 1
                8 -> text[7] = text[7] + 1
                9 -> text[8] = text[8] + 1
                10 -> text[9] = text[9] + 1
                11 -> text[10] = text[10] + 1
                12 -> text[11] = text[11] + 1
                13 -> text[12] = text[12] + 1

            }
        }
        var x = 0
        var message = ""
        for (it in text) {
            if (it != 0) {
                x += it
                when (it) {
                    2 -> message += "Elinizde bir ikili var\n"
                    3 -> message += "Elinizde bir üçlü var\n"
                    4 -> message += "Elinizde bir dörtlü var\n"
                }
                if (x >= 5)
                    break
            }
        }
        return message
    }


}
