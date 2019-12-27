package com.fcfm.kinedu

public class SpinnerItem {
    private var mSpinnerName: String? = null

    public constructor(SpinnerName: String){
        mSpinnerName = SpinnerName
    }

    public fun getSpinnerName():String?{
        return mSpinnerName
    }
}