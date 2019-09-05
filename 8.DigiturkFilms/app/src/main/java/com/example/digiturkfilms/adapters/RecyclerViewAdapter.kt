package com.example.digiturkfilms.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digiturkfilms.model.FilmModel
import com.example.digiturkfilms.viewholder.MoviesViewHolder

class RecyclerViewAdapter(private val filmList: ArrayList<FilmModel>) :
        RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder = MoviesViewHolder(parent)

    override fun getItemCount(): Int = filmList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(filmList[position])
    }
}