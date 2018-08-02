package com.dm.sharedelementtransitions.extension

import android.app.Activity
import android.os.Build
import android.support.annotation.StringRes
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.view.View
import android.view.Window

fun View.setTransitionName(@StringRes transitionNameResource: Int, prefix: String? = null) {
    val transitionName = context.getString(transitionNameResource)
    val transitionNameWithPrefix =
        if (prefix != null) "${prefix}_$transitionName" else transitionName
    ViewCompat.setTransitionName(this, transitionNameWithPrefix)
}

fun View.getSharedElement() = Pair(this, ViewCompat.getTransitionName(this))

fun Array<Pair<View, String>>.addStatusBarToSharedElements(activity: Activity) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val statusBarPair = Pair(
            activity.findViewById<View>(android.R.id.statusBarBackground),
            Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME
        )
        arrayOf(*this, statusBarPair)
    } else {
        this
    }
