package com.fcfm.kinedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.viewpager.widget.ViewPager
import com.fcfm.kinedu.Adapters.FragmentAdapter
import com.fcfm.kinedu.Adapters.SpinnerAdapter
import com.fcfm.kinedu.Utils.makeToast
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    var pager: ViewPager? = null
    var tabLayout: TabLayout? = null
    var spinner: Spinner? = null
    private var listSpinner: ArrayList<SpinnerItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager = vpMainActivity
        tabLayout = tabMainActivity

        initList()

        spinner = spAgeGroup
        //var adapterSpiner = ArrayAdapter.createFromResource(this, R.array.ageGroup, android.R.layout.simple_spinner_item)
        var adapterSpiner = SpinnerAdapter(this, listSpinner)
        //adapterSpiner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
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

    fun initList(){
        listSpinner = ArrayList()
        listSpinner!!.add(SpinnerItem("ALL MONTHS"))
        listSpinner!!.add(SpinnerItem("0-1 MONTH"))
        listSpinner!!.add(SpinnerItem("2 MONTHS"))
        listSpinner!!.add(SpinnerItem("3 MONTHS"))
        listSpinner!!.add(SpinnerItem("4 MONTHS"))
        listSpinner!!.add(SpinnerItem("5 MONTHS"))
        listSpinner!!.add(SpinnerItem("6 MONTHS"))
        listSpinner!!.add(SpinnerItem("7 MONTHS"))
        listSpinner!!.add(SpinnerItem("8 MONTHS"))
        listSpinner!!.add(SpinnerItem("9 MONTHS"))
        listSpinner!!.add(SpinnerItem("10 MONTHS"))
        listSpinner!!.add(SpinnerItem("11 MONTHS"))
        listSpinner!!.add(SpinnerItem("12 MONTHS"))
        listSpinner!!.add(SpinnerItem("13 MONTHS"))
        listSpinner!!.add(SpinnerItem("14 MONTHS"))
        listSpinner!!.add(SpinnerItem("15 MONTHS"))
        listSpinner!!.add(SpinnerItem("16 MONTHS"))
    }
}
