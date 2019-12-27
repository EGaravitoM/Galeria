package com.fcfm.kinedu.Models

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fcfm.kinedu.R
import com.squareup.picasso.Picasso

class ArticlePlaceHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.articlesdesign, parent, false)) {

    private var mActivityTitle: TextView? = null
    private var mActivityPurpose: TextView? = null
    private var mActivityImage: ImageView? = null


    init {
        mActivityTitle = itemView.findViewById(R.id.tvArticleNameList)
        mActivityPurpose = itemView.findViewById(R.id.tvArticleSDescriptionList)
        mActivityImage = itemView.findViewById(R.id.ivArticleImageList)
    }

    fun bindToAdapter(art:articles){
        mActivityTitle?.text = art.name
        mActivityPurpose?.text = art.short_description
        Picasso.get()
            .load(art.picture)
            .fit()
            .centerCrop()
            .into(mActivityImage)
    }

}