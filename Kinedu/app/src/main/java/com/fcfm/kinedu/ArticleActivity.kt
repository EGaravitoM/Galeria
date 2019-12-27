package com.fcfm.kinedu

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.htmlEncode
import com.fcfm.kinedu.Models.articles
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException

class ArticleActivity : AppCompatActivity() {

    private var mActivityTitle: TextView? = null
    private var mActivityPurpose: TextView? = null
    private var mActivityImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articledetail)

        mActivityTitle = findViewById(R.id.tvTitleDetailArticle)
        mActivityPurpose = findViewById(R.id.tvBodyDetailArticle)
        mActivityImage = findViewById(R.id.ivImageDeatilArticle)

        var id = intent.getIntExtra("id", 0)

        val serviceURL = "http://staging.kinedu.com/api/v3/articles/$id"
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

                val data2 = gson2.fromJson(body, reciever::class.java)

                runOnUiThread {
                    mActivityTitle?.text = data2?.data?.article?.title
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        mActivityPurpose?.setText(Html.fromHtml(data2?.data?.article?.body, Html.FROM_HTML_MODE_COMPACT))
                    } else {
                        mActivityPurpose?.setText(Html.fromHtml(data2?.data?.article?.body))
                    }
                    //mActivityPurpose?.text = data2?.data?.article?.body
                    Picasso.get()
                        .load(data2?.data?.article?.picture)
                        .into(mActivityImage)
                }

            }
        })



    }
}


data class reciever(
    var data: data
)

data class data (
    var article: article
)

data class article(
    var id: Int,
    var title: String,
    var picture:String?= null,
    var link: String,
    var area_id: Int,
    var body: String,
    var faved: Boolean
)