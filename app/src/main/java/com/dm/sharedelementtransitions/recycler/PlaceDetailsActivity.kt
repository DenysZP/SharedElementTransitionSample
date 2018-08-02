package com.dm.sharedelementtransitions.recycler

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import com.dm.sharedelementtransitions.R
import com.dm.sharedelementtransitions.adapter.Place
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_place_details.*
import java.lang.Exception


class PlaceDetailsActivity : AppCompatActivity() {

    companion object {

        private const val PLACE = "place"
        private const val NAME_TRANSITION = "name_transition"
        private const val IMAGE_TRANSITION = "image_transition"

        fun getStartIntent(
            context: Context,
            place: Place,
            nameTransition: String,
            imageTransition: String
        ): Intent {
            val intent = Intent(context, PlaceDetailsActivity::class.java)
            intent.putExtra(PLACE, place)
            intent.putExtra(NAME_TRANSITION, nameTransition)
            intent.putExtra(IMAGE_TRANSITION, imageTransition)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_details)

        val place = intent.getParcelableExtra<Place>(PLACE)
        val nameTransition = intent.getStringExtra(NAME_TRANSITION)
        val imageTransition = intent.getStringExtra(IMAGE_TRANSITION)

        ViewCompat.setTransitionName(placeName, nameTransition)
        ViewCompat.setTransitionName(placeImageView, imageTransition)

        supportPostponeEnterTransition()

        Picasso.get()
            .load(place.image)
            .into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    supportStartPostponedEnterTransition()
                    bitmap?.let {
                        placeImageView.setImageBitmap(bitmap)
                        changeStatusBarColor(bitmap)
                    }
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    supportStartPostponedEnterTransition()
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

                }
            })
        placeName.text = place.name
        placeDescription.text = place.description
    }

    private fun changeStatusBarColor(bitmap: Bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val defaultColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
            Palette.from(bitmap).generate {
                window.statusBarColor = it.getVibrantColor(defaultColor)
            }
        }
    }
}
