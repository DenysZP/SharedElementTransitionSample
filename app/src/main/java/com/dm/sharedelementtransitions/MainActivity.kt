package com.dm.sharedelementtransitions

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dm.sharedelementtransitions.activity.FirstActivity
import com.dm.sharedelementtransitions.fragment.FragmentHolderActivity
import com.dm.sharedelementtransitions.recycler.RecyclerViewActivity
import com.dm.sharedelementtransitions.viewpager.RecyclerViewToViewPagerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activitySharedElementButton.setOnClickListener {
            startActivity(Intent(this, FirstActivity::class.java))
        }

        fragmentSharedElementButton.setOnClickListener {
            startActivity(Intent(this, FragmentHolderActivity::class.java))
        }

        recyclerSharedElementButton.setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }

        recyclerViewPagerSharedElementButton.setOnClickListener {
            startActivity(Intent(this, RecyclerViewToViewPagerActivity::class.java))
        }
    }
}