package com.dm.sharedelementtransitions.recycler

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.dm.sharedelementtransitions.R
import com.dm.sharedelementtransitions.SpaceDecoration
import com.dm.sharedelementtransitions.adapter.ItemListAdapter
import com.dm.sharedelementtransitions.adapter.Place
import com.dm.sharedelementtransitions.adapter.PlaceItem
import com.dm.sharedelementtransitions.adapter.getSampleDataList
import com.dm.sharedelementtransitions.extension.addStatusBarToSharedElements
import kotlinx.android.synthetic.main.activity_recycler_view.*
import kotlinx.android.synthetic.main.item_place.view.*

class RecyclerViewActivity : AppCompatActivity(), PlaceItem.PlaceClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        setSupportActionBar(toolbar)

        val space = resources.getDimensionPixelSize(R.dimen.divider_size)
        val halfOfSpace = space / 2
        val decoration = SpaceDecoration(space, halfOfSpace, space, halfOfSpace, false)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItemListAdapter(getSampleDataList(), this)
        recyclerView.addItemDecoration(decoration)
    }

    override fun onPlaceClicked(place: Place, item: PlaceItem) {
        val nameTransition = ViewCompat.getTransitionName(item.name)
        val imageTransition = ViewCompat.getTransitionName(item.placeImageView)
        val sharedElements = arrayOf<Pair<View, String>>(
            Pair(item.name, nameTransition),
            Pair(item.placeImageView, imageTransition)
        ).addStatusBarToSharedElements(this)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            *sharedElements
        ).toBundle()
        startActivity(
            PlaceDetailsActivity.getStartIntent(
                this,
                place,
                nameTransition,
                imageTransition
            ), options
        )
    }
}
