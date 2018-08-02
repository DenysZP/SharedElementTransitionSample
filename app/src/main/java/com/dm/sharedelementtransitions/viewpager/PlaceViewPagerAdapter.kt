package com.dm.sharedelementtransitions.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.dm.sharedelementtransitions.adapter.Place

class PlaceViewPagerAdapter(
    private val placeDataList: List<Place>,
    fragmentManager: FragmentManager
) :
    FragmentStatePagerAdapter(fragmentManager) {

    private var isFadeEnabled = false

    override fun getItem(position: Int): Fragment {
        val fragment = PlaceDetailsFragment.newInstance(placeDataList[position], isFadeEnabled)
        isFadeEnabled = true
        return fragment
    }

    override fun getCount() = placeDataList.size
}