package com.fcfm.kinedu.Utils


import android.content.Context
import android.graphics.Bitmap
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.RelativeLayout
import android.widget.ProgressBar
import android.widget.Toast
import java.io.*



fun showLoadingBar(context:Context, roofLayoutRef:ConstraintLayout):ProgressBar{
    val progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleLarge)
    val params = RelativeLayout.LayoutParams(200, 200)
    params.addRule(RelativeLayout.CENTER_IN_PARENT)
    roofLayoutRef.addView(progressBar, params)

    progressBar.isIndeterminate = true
    progressBar.visibility = View.VISIBLE

    return progressBar
}

fun hideLoadingBar(progressBarRef: ProgressBar){
    progressBarRef.visibility = View.GONE
}

fun makeToast(context:Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun bitmapToByteArray(bmp:Bitmap): ByteArray{
    val stream = ByteArrayOutputStream()
    bmp.compress(Bitmap.CompressFormat.JPEG, 10, stream)
    val byteArray = stream.toByteArray()

    return byteArray
}

private fun getStringResourceByName(context: Context,aString: String): String {
    val packageName = context.packageName
    val resId = context.resources.getIdentifier(aString, "string", packageName)
    return context.getString(resId)
}