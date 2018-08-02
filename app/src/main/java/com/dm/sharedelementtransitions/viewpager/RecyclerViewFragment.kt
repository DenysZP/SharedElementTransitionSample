package com.dm.sharedelementtransitions.viewpager

import android.os.Build
import android.os.Bundle
import android.support.transition.TransitionInflater
import android.support.transition.TransitionSet
import android.support.v4.app.Fragment
import android.support.v4.app.SharedElementCallback
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLayoutChangeListener
import android.view.ViewGroup
import com.dm.sharedelementtransitions.R
import com.dm.sharedelementtransitions.SpaceDecoration
import com.dm.sharedelementtransitions.adapter.ItemListAdapter
import com.dm.sharedelementtransitions.adapter.Place
import com.dm.sharedelementtransitions.adapter.PlaceItem
import com.dm.sharedelementtransitions.adapter.getSampleDataList
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import kotlinx.android.synthetic.main.item_place.view.*


class RecyclerViewFragment : Fragment(), PlaceItem.PlaceClickListener,
    PlaceItem.ImageLoadingListener {

    private val data = getSampleDataList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)

        val space = resources.getDimensionPixelSize(R.dimen.divider_size)
        val halfOfSpace = space / 2
        val decoration = SpaceDecoration(space, halfOfSpace, space, halfOfSpace, false)
        val adapter = ItemListAdapter(data, this, this)
        adapter.setHasStableIds(true)
        recyclerView.layoutManager = GridLayoutManager(view.context, 2)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(decoration)

        prepareTransitions()
        postponeEnterTransition()
        scrollToPosition()
    }

    override fun onPlaceClicked(place: Place, item: PlaceItem) {
        (exitTransition as TransitionSet).excludeTarget(item, true)
        fragmentManager?.let {
            val index = data.indexOf(place)
            val position = if (index >= 0) index else 0
            RecyclerViewToViewPagerActivity.currentPosition = position
            val itemTransitionName = "${place.id}_item"
            ViewCompat.setTransitionName(item, itemTransitionName)
            val fragment = ViewPagerFragment.newInstance(position, data)
            it.beginTransaction()
                .setReorderingAllowed(true)
                .addSharedElement(
                    item.placeImageView,
                    ViewCompat.getTransitionName(item.placeImageView)
                )
                .addSharedElement(item.name, ViewCompat.getTransitionName(item.name))
                .addSharedElement(item, itemTransitionName)
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onImageLoadingFinished() {
        startPostponedEnterTransition()
    }

    private fun prepareTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val transition = TransitionInflater.from(context)
                .inflateTransition(R.transition.grid_exit_transition)
            transition.duration = resources.getInteger(R.integer.duration).toLong()
            exitTransition = transition
            setExitSharedElementCallback(object : SharedElementCallback() {

                override fun onMapSharedElements(
                    names: MutableList<String>,
                    sharedElements: MutableMap<String, View>
                ) {
                    val selectedViewHolder = recyclerView
                        .findViewHolderForAdapterPosition(RecyclerViewToViewPagerActivity.currentPosition)
                    if (selectedViewHolder?.itemView == null) {
                        return
                    }

                    sharedElements[names[0]] =
                            selectedViewHolder.itemView.findViewById(R.id.placeImageView)
                    sharedElements[names[1]] =
                            selectedViewHolder.itemView.findViewById(R.id.name)
                }
            })
        }
    }

    private fun scrollToPosition() {
        recyclerView.addOnLayoutChangeListener(object : OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                recyclerView.removeOnLayoutChangeListener(this)
                val layoutManager = recyclerView.layoutManager
                val viewAtPosition =
                    layoutManager.findViewByPosition(RecyclerViewToViewPagerActivity.currentPosition)
                // Scroll to position if the view for the current position is null (not currently part of
                // layout manager children), or it's not completely visible.
                if (viewAtPosition == null || layoutManager
                        .isViewPartiallyVisible(viewAtPosition, false, true)
                ) {
                    recyclerView.post {
                        layoutManager.scrollToPosition(
                            RecyclerViewToViewPagerActivity.currentPosition
                        )
                    }
                }
            }
        })
    }
}