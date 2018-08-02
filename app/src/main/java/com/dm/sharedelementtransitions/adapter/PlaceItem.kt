package com.dm.sharedelementtransitions.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import com.dm.sharedelementtransitions.R
import com.dm.sharedelementtransitions.extension.setTransitionName
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_place.view.*
import java.lang.Exception

class PlaceItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var placeClickListener: PlaceClickListener? = null
    var imageLoadingListener: ImageLoadingListener? = null

    init {
        View.inflate(context, R.layout.item_place, this)
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    }

    fun bind(place: Place) {
        Picasso.get()
            .load(place.image)
            .into(placeImageView, object : Callback {
                override fun onSuccess() {
                    imageLoadingListener?.onImageLoadingFinished()
                }

                override fun onError(e: Exception?) {
                    imageLoadingListener?.onImageLoadingFinished()
                }
            })
        name.text = place.name

        setupTransition(place.id)
        setOnClickListener { placeClickListener?.onPlaceClicked(place, this) }
    }

    private fun setupTransition(id: Long) {
        val prefix = id.toString()
        placeImageView.setTransitionName(R.string.image_transition, prefix)
        name.setTransitionName(R.string.name_transition, prefix)
    }

    interface PlaceClickListener {

        fun onPlaceClicked(place: Place, item: PlaceItem)
    }

    interface ImageLoadingListener {

        fun onImageLoadingFinished()
    }
}