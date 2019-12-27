package com.fcfm.kinedu.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fcfm.kinedu.Fragments.reciever
import com.fcfm.kinedu.Models.*

class ArticleAdapter(
    private val info: reciever,
    private val context: Context,
    private val onClickListener: (View, articles) -> Unit
) :

    RecyclerView.Adapter<ArticlePlaceHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlePlaceHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArticlePlaceHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ArticlePlaceHolder, position: Int) {
        val post: articles = info.data.articles[position]
        holder.bindToAdapter(post)

        holder.itemView.setOnClickListener{view ->
            onClickListener.invoke(view, post)
        }
    }

    override fun getItemCount(): Int = info.data.articles.count()
}
