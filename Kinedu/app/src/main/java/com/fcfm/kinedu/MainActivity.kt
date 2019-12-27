package com.fcfm.kinedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.viewpager.widget.ViewPager
import com.fcfm.kinedu.Adapters.FragmentAdapter
import com.fcfm.kinedu.Utils.makeToast
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    var pager: ViewPager? = null
    var tabLayout: TabLayout? = null
    var spinner: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager = vpMainActivity
        tabLayout = tabMainActivity

        spinner = spAgeGroup
        var adapterSpiner = ArrayAdapter.createFromResource(this, R.array.ageGroup, android.R.layout.simple_spinner_item)
        adapterSpiner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = adapterSpiner

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

        }

        val adapter = FragmentAdapter(this,
            supportFragmentManager,
            tabLayout!!.tabCount)

        pager!!.adapter = adapter
        pager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager!!.currentItem = tab!!.position
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }
        })

    }

    override fun onStart() {
        super.onStart()
        if(intent.hasExtra("loggedUser")) {
            val welcomeMessage =
                resources.getString(R.string.welcomeMsg) + " " + intent.getStringExtra("loggedUser")
            makeToast(this, welcomeMessage)
        }
    }
}
