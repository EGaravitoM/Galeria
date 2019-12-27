package com.fcfm.kinedu.Fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fcfm.kinedu.Adapters.ArticleAdapter
import com.fcfm.kinedu.ArticleActivity
import com.fcfm.kinedu.Models.activities
import com.fcfm.kinedu.Models.articles
import com.fcfm.kinedu.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.lvarticles.*
import okhttp3.*
import java.io.IOException

class ArticlesFragment(myFeed:Boolean) : Fragment(){

    private var sharedPreferences: SharedPreferences? = null
    private val sharedPreferencesFilename = "com.proyectofinal.travelview.userPrefs"
    private val serviceURL = "https://travelview.herokuapp.com/"
    private val allPosts = myFeed

    companion object{
        fun newInstance(myFeed: Boolean): ActivityFragment{
            return ActivityFragment(myFeed)
        }
    }

    private var postList:MutableList<activities>? = null
    private var baseLayout: ConstraintLayout? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.lvarticles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseLayout = view?.findViewById(R.id.clArticles) as ConstraintLayout

        sharedPreferences = activity!!.getSharedPreferences(sharedPreferencesFilename, Context.MODE_PRIVATE)

        fetchJSON()

    }

    fun fetchJSON(){

        println("Attempting to fetch JSON")

        val serviceURL = "http://staging.kinedu.com/api/v3/catalogue/articles?skill_id=5&baby_id=2064732"
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

                val data = gson.fromJson(body, reciever::class.java)

                getActivity()?.runOnUiThread {
                    var dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                    dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider))
                    recyclerArticles.addItemDecoration(dividerItemDecoration)
                    recyclerArticles.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = ArticleAdapter(
                            data!!,
                            activity!!,
                            onClickListener = { view, art -> openDetailArticle(view, art.id) }
                        )
                    }
                }
            }
        })
    }

    fun openDetailArticle(view: View, art:Int){

        getActivity()?.runOnUiThread {
            val intent = Intent(activity!!, ArticleActivity::class.java)
            intent.putExtra("id", art)
            startActivity(intent)
        }

      /*  println("Attempting to fetch specific JSON")

        data class article2(
            var id: Int,
            var title: String,
            var picture:String?= null,
            var link: String,
            var area_id: Int,
            var body: String,
            var faved: Boolean
        )

        data class data2 (
            var article: article2
        )

        data class reciever2(
            var data: data2
        )

        val serviceURL = "http://staging.kinedu.com/api/v3/articles/$art"
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

                val gson2 = GsonBuilder().create()

                val data2 = gson2.fromJson(body, reciever2::class.java)



            }
        })*/

    }


}

data class reciever(
    var data: data
)

data class data (
    var articles: List<articles>
)
