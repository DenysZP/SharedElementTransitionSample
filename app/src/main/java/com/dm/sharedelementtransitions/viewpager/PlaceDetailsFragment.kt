package com.dm.sharedelementtransitions.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dm.sharedelementtransitions.R
import com.dm.sharedelementtransitions.adapter.Place
import com.dm.sharedelementtransitions.extension.setTransitionName
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_place_details.*


class PlaceDetailsFragment : Fragment() {

    companion object {

        private const val PLACE = "place"
        private const val IS_FADE_ENABLED = "IS_FADE_ENABLED"

        fun newInstance(place: Place, isFadeEnabled: Boolean): Fragment {
            val fragment = PlaceDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(PLACE, place)
            bundle.putBoolean(IS_FADE_ENABLED, isFadeEnabled)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val place: Place? = arguments?.getParcelable(PLACE)
        val isFadeEnabled = arguments?.getBoolean(IS_FADE_ENABLED) ?: false
        if (place != null) {

            placeImageView.setTransitionName(R.string.image_transition, place.id.toString())
            placeName.setTransitionName(R.string.name_transition, place.id.toString())

            val request = Picasso.get()
                .load(place.image)
            if (!isFadeEnabled) {
                request.noFade()
            }
            request.into(placeImageView, object : Callback {
                override fun onSuccess() {
                    parentFragment?.startPostponedEnterTransition()
                }

                override fun onError(e: Exception?) {
                    parentFragment?.startPostponedEnterTransition()
                }
            })
            placeName.text = place.name
        }
    }
}