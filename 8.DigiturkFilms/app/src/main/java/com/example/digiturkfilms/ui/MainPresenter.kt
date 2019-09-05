package com.example.digiturkfilms.ui

import com.example.digiturkfilms.network.SendRequest

class MainPresenter (private val view: MainContract.View) : MainContract.Presenter {
    override fun getFilms() {
        //view.showProgress()

        SendRequest.getFilmList(
            { _, _response ->
                view.setGenreList(_response.body()!!.genres)
                view.hideProgress()
            },

            { _, _failure ->
                view.onFailure(_failure.message!!)
                view.hideProgress()
            }
        )
    }

}