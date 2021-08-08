package com.app.source.module.binding

import android.content.res.Resources
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun visibleGone(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("visibleInvisible")
    fun visibleInvisible(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("textRes")
    fun setTextResource(textView: TextView, msg: Any?) {
        msg?.let {
            try {
                val value = if (it is Int) textView.context.getString(it) else msg.toString()
                textView.text = value
            } catch (ex: Resources.NotFoundException) {
                ex.localizedMessage
            }
        }
    }
}