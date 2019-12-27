package com.fcfm.kinedu.Models

import android.graphics.Bitmap

data class activities(
    var id: Int,
    var name: String,
    var age: Int,
    var age_group: String,
    var activityType: String,
    var is_holiday: String,
    var domain_id: Int,
    var area_id: Int,
    var purpose : String,
    var thumbnail:String?= null,
    var active_milestones: Int,
    var completed_milestones: Int,
    var locked: Boolean,
    var dap_lifes_checked: Boolean,
    var imageBitmap: Bitmap? = null
)