package com.dm.sharedelementtransitions.utils

import android.view.View
import com.dm.sharedelementtransitions.extension.getSharedElement

fun getSharedElements(vararg views: View) = views.map { it.getSharedElement() }.toTypedArray()