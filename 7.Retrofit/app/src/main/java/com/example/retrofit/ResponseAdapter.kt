package com.example.retrofit


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.ResponseModel

class ResponseAdapter(
    val repos: ResponseModel,
    val onClickListener: (items: Items) -> Unit
) : RecyclerView.Adapter<ResponseAdapter.ResponseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResponseViewHolder = ResponseViewHolder(parent)

    override fun getItemCount(): Int = repos.items.size

    override fun onBindViewHolder(holder: ResponseViewHolder, position: Int) {
        holder.bind(repos.items[position], position, onClickListener)
    }

    inner class ResponseViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.rv_row, parent, false)
    ) {
        fun bind(
            repo: Items,
            position: Int,
            onItemClickListener: (items: Items) -> Unit
        ) {
            itemView.findViewById<TextView>(R.id.row_count).text = position.toString()
            itemView.findViewById<TextView>(R.id.repo_name).text = repo.name
            //itemView.findViewById<TextView>(R.id.user_name).text = repo.owner
            itemView.findViewById<TextView>(R.id.repo_language).text = repo.language
            itemView.findViewById<TextView>(R.id.star_count).text = repo.stargazers_count.toString()
            itemView.setOnClickListener {onItemClickListener(repo)}

        }
    }


}
