package com.example.askereslestir.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.askereslestir.R
import com.example.askereslestir.model.ListeModel

class ListeAdapter (
    val names: ArrayList<ListeModel>,
    val onItemClickListener:(listeModel: ListeModel) -> Unit): RecyclerView.Adapter<ListeAdapter.ListeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListeViewHolder = ListeViewHolder(parent)

    override fun getItemCount(): Int = names.size

    override fun onBindViewHolder(holder: ListeViewHolder, position: Int) {
        holder.bind(names[position],onItemClickListener)
    }
    inner class ListeViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.print_lay, parent, false)
    ) {
        val name : TextView by lazy { itemView.findViewById<TextView>(R.id.rv_text_box) }
        fun bind(listeModel: ListeModel, onItemClickListener: (listeModel: ListeModel) -> Unit) {
            name.text = listeModel.text
            itemView.setOnClickListener {onItemClickListener(listeModel)}

        }



    }
}