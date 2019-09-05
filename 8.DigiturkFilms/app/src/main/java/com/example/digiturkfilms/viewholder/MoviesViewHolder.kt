package com.example.digiturkfilms.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digiturkfilms.BuildConfig
import com.example.digiturkfilms.R
import com.example.digiturkfilms.model.FilmModel
import com.squareup.picasso.Picasso

class MoviesViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.rv_core, parent, false)
) {

    private val img: ImageView by lazy { itemView.findViewById<ImageView>(R.id.core_img) }
    private val text: TextView by lazy { itemView.findViewById<TextView>(R.id.core_name) }

    fun bind(filmModel: FilmModel) {
        Picasso.get()
            .load(BuildConfig.API_POSTER_URL + filmModel.poster_path)
            .into(img)
        text.text = filmModel.title
    }
}