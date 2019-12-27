package com.fcfm.kinedu.Models

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fcfm.kinedu.R
import com.fcfm.kinedu.Utils.CircleTransform
import com.fcfm.kinedu.Utils.RoundedCornersTransformation
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

class ActivityPlaceHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.activitiesdesign, parent, false)) {

    private var mActivityTitle: TextView? = null
    private var mActivityPurpose: TextView? = null
    private var mActivityImage: ImageView? = null


    init {
        mActivityTitle = itemView.findViewById(R.id.tvTitleActivity)
        mActivityPurpose = itemView.findViewById(R.id.tvDescriptionActivity)
        mActivityImage = itemView.findViewById(R.id.ivActivity)
    }

    fun bindToAdapter(act:activities){

        var radius = 16
        var margin = 16
        var transformation = RoundedCornersTransformation(radius, margin)

        mActivityTitle?.text = act.name
        mActivityPurpose?.text = act.purpose
        Picasso.get()
            .load(act.thumbnail)
            //.resizeDimen(R.dimen.image_size300dp,R.dimen.image_size300dp)
            .fit()
            .centerCrop()
            .transform(transformation)
            .into(mActivityImage)
    }

}