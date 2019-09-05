package com.example.digiturkfilms.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.digiturkfilms.R
import com.example.digiturkfilms.adapters.ViewPagerAdapter
import com.example.digiturkfilms.model.GenreModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.vp_fragment.*

class MainActivity : FragmentActivity(), MainContract.View {

    private val mPresenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressFrame.setOnClickListener {

        }

        mPresenter.getFilms()
    }

    override fun setGenreList(genreList: List<GenreModel>) {
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, genreList)
    }

    override fun onFailure(message: String) {
        println(message)
    }

    override fun showProgress() {
        //progressBar.visibility = View.VISIBLE
        progressFrame.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        //progressBar.visibility = View.GONE
        progressFrame.visibility = View.GONE
    }


}
