package com.fcfm.kinedu.Adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fcfm.kinedu.Fragments.ActivityFragment
import com.fcfm.kinedu.Fragments.ArticlesFragment

class FragmentAdapter(val context: Context,
                      fragmentManager: FragmentManager,
                      private var totalTabs:Int)
    : FragmentPagerAdapter(fragmentManager){


    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {
                return ActivityFragment(false)
            }
            1 -> {
                return ArticlesFragment(true)
            }
        }
        return ActivityFragment(false)
    }

    override fun getCount(): Int {
        return totalTabs
    }

}