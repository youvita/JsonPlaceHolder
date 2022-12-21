package com.source.module.app

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.source.module.util.Constants

fun Fragment.recreateLanguageChanged() {
    activity?.let {
        it.intent.putExtra(Constants.CHANGED_LANGUAGE, Constants.CHANGED)
        it.finish()
        startActivity(it.intent)
    }
}

/**
 * intent parcelable object
 * */
inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}
