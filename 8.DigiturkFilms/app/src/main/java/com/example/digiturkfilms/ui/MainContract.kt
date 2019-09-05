package com.example.digiturkfilms.ui

import com.example.digiturkfilms.base.BaseContract
import com.example.digiturkfilms.model.GenreModel

class MainContract {
    interface View : BaseContract.View{
        fun setGenreList(genreList: List<GenreModel>)
        fun onFailure(message: String)
    }

    interface Presenter: BaseContract.Presenter<View>{
        fun getFilms()
    }
}