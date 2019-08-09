package com.example.askerekleme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.askerekleme.R
import com.example.askerekleme.model.CheckBoxModel
import kotlinx.android.synthetic.main.check_box.view.*


class CheckBoxAdapter(
    private val names: ArrayList<CheckBoxModel>,
    private val onItemClickListener: (checkBoxModel: CheckBoxModel) -> Unit
): RecyclerView.Adapter<CheckBoxAdapter.CheckBoxViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckBoxViewHolder = CheckBoxViewHolder(parent)

    override fun getItemCount(): Int = names.size

    override fun onBindViewHolder(holder: CheckBoxViewHolder, position: Int) {
        holder.bind(names[position],onItemClickListener)
    }

    inner class CheckBoxViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.check_box, parent, false)
    ){
        val cityName : CheckBox by lazy { itemView.findViewById<CheckBox>(R.id.checkBox) }
        fun bind(cbModel: CheckBoxModel, onItemClickListener: (mCbModel: CheckBoxModel) -> Unit){

            cityName.text = cbModel.name
            itemView.setOnClickListener {onItemClickListener(cbModel)}
        }

    }
}

