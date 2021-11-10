package com.app.source.module.app

import android.util.DisplayMetrics
import androidx.fragment.app.Fragment

/**
 * Calculate window height for fullscreen
 */
fun Fragment.getWindowHeight(): Int {
    val displayMetrics = DisplayMetrics()
    context?.display?.getRealMetrics(displayMetrics)
    return displayMetrics.heightPixels
}