/**
 * Copyright (c) SkyBooking Co,. Ltd
 * SkyOwner
 * Created by Khunchheang on  18/1/2020.
 */

package com.source.module.custom

import android.graphics.drawable.GradientDrawable

class DotsGradientDrawable : GradientDrawable() {

   var currentColor: Int = 0

   override fun setColor(argb: Int) {
      super.setColor(argb)
      currentColor = argb
   }
}