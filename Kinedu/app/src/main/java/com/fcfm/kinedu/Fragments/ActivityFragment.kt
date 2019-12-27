package com.fcfm.kinedu.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fcfm.kinedu.Adapters.ActivityAdapter
import com.fcfm.kinedu.Models.Reciever
import com.fcfm.kinedu.Models.activities
import androidx.recyclerview.widget.DividerItemDecoration
import com.fcfm.kinedu.R
import com.google.gson.*
import kotlinx.android.synthetic.main.lvactivities.*
import okhttp3.*
import java.io.IOException

class ActivityFragment(myFeed:Boolean) : Fragment(){

    private var sharedPreferences: SharedPreferences? = null
    private val sharedPreferencesFilename = "com.proyectofinal.travelview.userPrefs"


    private val allPosts = myFeed

    companion object{
        fun newInstance(myFeed: Boolean): ActivityFragment{
            return ActivityFragment(myFeed)
        }
    }

    private var ActivityList:MutableList<activities>? = null
    private var baseLayout: ConstraintLayout? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.lvactivities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseLayout = view?.findViewById(R.id.clActivities) as ConstraintLayout

        sharedPreferences = activity!!.getSharedPreferences(sharedPreferencesFilename, Context.MODE_PRIVATE)

        ActivityList = mutableListOf()

        fetchJSON()

    }

    fun fetchJSON(){

        println("Attempting to fetch JSON")

        val serviceURL = "http://staging.kinedu.com/api/v3/catalogue/activities?skill_id=5&baby_id=2064732"
        val client = OkHttpClient()

        val request = Request.Builder().url(serviceURL).header(
            "Authorization",
            "Token token=5105f4358e45f6f98057a654c882b7742c3ac5241c81a706acc48c84f8acde9f8a344993ac42369627ae9f2caf1eed42ff1be9562fe2167c9c80908e76e95c49"
            ).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
                println(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                //println(body)

                val gson = GsonBuilder().create()

                val data = gson.fromJson(body, Reciever::class.java)

                getActivity()?.runOnUiThread {
                    var dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                    dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider))
                    recycleAcrivities.addItemDecoration(dividerItemDecoration)
                    recycleAcrivities.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter =  ActivityAdapter(
                            data!!,
                            activity!!
                        )
                    }
                }

            }
        })
    }


}