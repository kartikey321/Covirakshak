package com.example.dell.covidtweets

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dell.covidtweets.fragments.HomeFragment
import com.example.dell.covidtweets.fragments.ProfileFragment
import com.example.dell.covidtweets.fragments.SearchFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class MainActivity : AppCompatActivity() {
    var menuBottom:ChipNavigationBar?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menuBottom=findViewById<View>(R.id.menu_bottom) as ChipNavigationBar

        val homeFragment = HomeFragment()
        val searchFragment= SearchFragment()
        val profileFragment= ProfileFragment()

        makeCurrentFragment(homeFragment)

        menuBottom!!.setOnItemSelectedListener(object : ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                var fragment: Fragment? = null
                when (id) {
                    R.id.home -> fragment = HomeFragment()
                    R.id.add -> fragment = SearchFragment()
                    R.id.profile -> fragment = ProfileFragment()
                }
                if (fragment != null) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragement_container, fragment)
                        .commit()
                } else {
                    Log.e("TAG", "Error in creating fragment")
                }
            }
        })



    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragement_container, fragment)
            commit()
        }

    }
}