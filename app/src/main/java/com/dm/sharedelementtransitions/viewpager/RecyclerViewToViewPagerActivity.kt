package com.dm.sharedelementtransitions.viewpager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dm.sharedelementtransitions.R

class RecyclerViewToViewPagerActivity : AppCompatActivity() {

    companion object {
        var currentPosition = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_to_view_pager)

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(
                R.id.fragmentContainer,
                RecyclerViewFragment()
            )
            .commit()
    }
}