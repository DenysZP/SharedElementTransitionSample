package com.dm.sharedelementtransitions.viewpager

import android.os.Build
import android.os.Bundle
import android.support.transition.TransitionInflater
import android.support.v4.app.Fragment
import android.support.v4.app.SharedElementCallback
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dm.sharedelementtransitions.R
import com.dm.sharedelementtransitions.adapter.Place
import kotlinx.android.synthetic.main.fragment_view_pager.*

class ViewPagerFragment : Fragment() {

    companion object {

        private const val POSITION = "position"
        private const val PLACE_DATA_LIST = "place_data_list"

        fun newInstance(position: Int, placeDataList: List<Place>): Fragment {
            val fragment = ViewPagerFragment()
            val bundle = Bundle()
            bundle.putInt(POSITION, position)
            bundle.putParcelableArrayList(PLACE_DATA_LIST, ArrayList(placeDataList))
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            postponeEnterTransition()
        }
        prepareTransitions()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentItem = arguments?.getInt(POSITION) ?: 0
        val data = arguments?.getParcelableArrayList(PLACE_DATA_LIST) ?: listOf<Place>()

        val animalPagerAdapter = PlaceViewPagerAdapter(data, childFragmentManager)
        viewPager.adapter = animalPagerAdapter
        viewPager.currentItem = currentItem
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                RecyclerViewToViewPagerActivity.currentPosition = position
            }
        })
    }

    private fun prepareTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val transition = TransitionInflater.from(context)
                .inflateTransition(R.transition.image_shared_element_transition)
            transition.duration = resources.getInteger(R.integer.duration).toLong()
            sharedElementEnterTransition = transition

            setEnterSharedElementCallback(object : SharedElementCallback() {
                override fun onMapSharedElements(
                    names: MutableList<String>,
                    sharedElements: MutableMap<String, View>
                ) {
                    val currentFragment = viewPager.adapter!!.instantiateItem(
                        viewPager,
                        RecyclerViewToViewPagerActivity.currentPosition
                    ) as Fragment
                    val view = currentFragment.view ?: return

                    sharedElements[names[0]] = view.findViewById(R.id.placeImageView)
                    sharedElements[names[1]] = view.findViewById(R.id.placeName)
                }
            })
        }
    }
}