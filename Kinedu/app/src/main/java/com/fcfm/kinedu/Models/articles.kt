package com.fcfm.kinedu.Models

import android.graphics.Bitmap

data class articles(
    var type: String,
    var id: Int,
    var name: String,
    var min_age: Int,
    var max_age: Int,
    var picture:String?= null,
    var area_id: Int,
    var short_description : String,
    var imageBitmap: Bitmap? = null
)