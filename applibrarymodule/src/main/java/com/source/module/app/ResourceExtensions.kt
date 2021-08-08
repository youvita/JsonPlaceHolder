package com.source.module.app

import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.source.module.util.Constants

fun Fragment.recreateLanguageChanged() {
    activity?.let {
        it.intent.putExtra(Constants.CHANGED_LANGUAGE, Constants.CHANGED)
        it.finish()
        startActivity(it.intent)
    }
}