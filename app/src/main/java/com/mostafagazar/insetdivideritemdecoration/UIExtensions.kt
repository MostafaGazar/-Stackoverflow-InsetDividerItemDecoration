package com.mostafagazar.insetdivideritemdecoration

import android.content.res.Resources

fun Int.toPx() = (this * Resources.getSystem().displayMetrics.density).toInt()