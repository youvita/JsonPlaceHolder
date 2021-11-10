package com.source.module.app

import androidx.fragment.app.Fragment
import com.source.module.util.Constants

fun Fragment.recreateLanguageChanged() {
    activity?.let {
        it.intent.putExtra(Constants.CHANGED_LANGUAGE, Constants.CHANGED)
        it.finish()
        startActivity(it.intent)
    }
}
