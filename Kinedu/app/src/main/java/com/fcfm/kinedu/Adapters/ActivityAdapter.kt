package com.fcfm.kinedu.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fcfm.kinedu.Models.activities
import com.fcfm.kinedu.Models.ActivityPlaceHolder
import com.fcfm.kinedu.Models.Reciever

class ActivityAdapter(
    private val info: Reciever,
    private val context: Context
) :

    RecyclerView.Adapter<ActivityPlaceHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityPlaceHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ActivityPlaceHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ActivityPlaceHolder, position: Int) {
        val post:activities = info.data.activities[position]
        holder.bindToAdapter(post)

        holder.itemView.setOnClickListener{view ->
        }
    }

    override fun getItemCount(): Int = info.data.activities.count()
}
