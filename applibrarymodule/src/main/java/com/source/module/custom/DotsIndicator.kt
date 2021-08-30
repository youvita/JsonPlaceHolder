package com.source.module.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.source.module.R
import java.util.*

class DotsIndicator @JvmOverloads constructor(
   context: Context,
   attrs: AttributeSet? = null,
   defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

   private var currentItemPos: Int = 1
   private var mCurrentItemOffset: Int = 0

   private val pagerSnapHelper = PagerSnapHelper()

   private var dots: MutableList<ImageView>? = null
   private var viewPager: RecyclerView? = null
   private var dotsSize: Float = 0.toFloat()
   private var dotsCornerRadius: Float = 0.toFloat()
   private var dotsSpacing: Float = 0.toFloat()
   private var dotsWidthFactor: Float = 0.toFloat()
   var dotsColor: Int = 0
   var selectedDotColor: Int = 0
   private var progressMode: Boolean = false
   var dotCount: Int? = null

   init {
      init(attrs)
   }

   private fun init(attrs: AttributeSet?) {
      dots = ArrayList()
      orientation = HORIZONTAL

      dotsSize = dpToPx(16).toFloat() // 16dp
      dotsSpacing = dpToPx(4).toFloat() // 4dp
      dotsCornerRadius = dotsSize / 2

      dotsWidthFactor = DEFAULT_WIDTH_FACTOR
      dotsColor = DEFAULT_POINT_COLOR

      if (attrs != null) {
         val a = context.obtainStyledAttributes(attrs, R.styleable.DotsIndicator)
         dotsColor = a.getColor(R.styleable.DotsIndicator_dotsColor, DEFAULT_POINT_COLOR)
         selectedDotColor =
            a.getColor(R.styleable.DotsIndicator_selectedDotColor, DEFAULT_POINT_COLOR)
         dotsWidthFactor = a.getFloat(R.styleable.DotsIndicator_dotsWidthFactor, 2.5f)
         if (dotsWidthFactor < 1) dotsWidthFactor = 2.5f
         dotsSize = a.getDimension(R.styleable.DotsIndicator_dotsSize, dotsSize)
         dotsCornerRadius =
            a.getDimension(R.styleable.DotsIndicator_dotsCornerRadius, dotsSize / 2).toInt()
               .toFloat()
         dotsSpacing = a.getDimension(R.styleable.DotsIndicator_dotsSpacing, dotsSpacing)
         progressMode = a.getBoolean(R.styleable.DotsIndicator_progressMode, false)
         a.recycle()
      }
      if (isInEditMode) addDots(5)
      refreshDots()
   }

   private fun refreshDots() {
      if (viewPager != null && viewPager!!.adapter != null) {
         post {
            // Check if we need to refresh the dots count
            refreshDotsCount()
            refreshDotsColors()
            refreshDotsSize()
            refreshOnPageChangedListener()
         }
      }
   }

   private fun refreshDotsCount() {
      if (dots!!.size < viewPager!!.adapter!!.itemCount) {
         if (dotCount == null) addDots(viewPager!!.adapter?.itemCount?.minus(dots!!.size)!!)
         else addDots(dotCount!!)
      } else if (dots!!.size > viewPager!!.adapter!!.itemCount) {
         if (dotCount == null) removeDots(dots!!.size - viewPager!!.adapter?.itemCount!!)
         else removeDots(dotCount!!)
      }
   }

   private fun addDots(count: Int) {
      for (i in 0 until count) {
         val dotView = LayoutInflater.from(context).inflate(R.layout.item_indicator, this, false)
         val params = dotView.findViewById<ImageView>(R.id.imgDotIndicator).layoutParams as RelativeLayout.LayoutParams
         params.height = dotsSize.toInt()
         params.width = params.height
         params.setMargins(dotsSpacing.toInt(), 0, dotsSpacing.toInt(), 0)
         val background = DotsGradientDrawable()
         background.cornerRadius = dotsCornerRadius
         if (isInEditMode) background.setColor(if (0 == i) selectedDotColor else dotsColor)
         else background.setColor(if (currentItemPos % dotCount!! == i) selectedDotColor else dotsColor)
         dotView.findViewById<ImageView>(R.id.imgDotIndicator).background = background
         dots!!.add(dotView.findViewById<ImageView>(R.id.imgDotIndicator))
         addView(dotView)
      }
   }

   private fun removeDots(count: Int) {
      for (i in 0 until count) {
         removeViewAt(childCount - 1)
         dots!!.removeAt(dots!!.size - 1)
      }
   }

   private fun refreshOnPageChangedListener() {
      if (viewPager != null && viewPager!!.adapter != null && viewPager!!.adapter!!.itemCount > 0) {
//         setImageBackground(dots!![currentItemPos % dotCount!!], selectedDotColor)
//         viewPager!!.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//               val virtualPosition = position % dotCount!!
//               for (i in 0 until dotCount!!) {
//                  if (i != virtualPosition) {
//                     setImageBackground(dots!![i], dotsColor)
//                  }
//               }
//               setImageBackground(dots!![virtualPosition], selectedDotColor)
//            }
//         })

         viewPager?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
               super.onScrolled(recyclerView, dx, dy)
//               if (dx != 0) {
//                  mCurrentItemOffset += dx
//               }
               val offset: Int = recyclerView.computeHorizontalScrollOffset()
               if (offset % recyclerView.width == 0) {
                  currentItemPos = offset / recyclerView.width
               }
               val virtualPosition = currentItemPos % dotCount!!
               for (i in 0 until dotCount!!) {
                  if (i != virtualPosition) {
                     setImageBackground(dots!![i], dotsColor)
                  }
               }
               setImageBackground(dots!![virtualPosition], selectedDotColor)
            }
         })
      }
   }

//   private fun getCurrect

   private fun setImageBackground(imageView: ImageView, color: Int) {
      val dotBg = imageView.background as DotsGradientDrawable
      dotBg.setColor(color)
   }

   private fun setDotWidth(dot: ImageView, dotWidth: Int) {
      val dotParams = dot.layoutParams
      dotParams.width = dotWidth
      dot.layoutParams = dotParams
   }

   private fun refreshDotsColors() {
      if (dots == null) {
         return
      }
      for (i in dots!!.indices) {
         val elevationItem = dots!![i]
         val background = elevationItem.background as DotsGradientDrawable

         if (i == currentItemPos || progressMode && i < currentItemPos) {
            background.setColor(selectedDotColor)
         } else {
            background.setColor(dotsColor)
         }

         elevationItem.background = background
         elevationItem.invalidate()
      }
   }

   private fun refreshDotsSize() {
      if (dots == null) return
      for (i in 0 until if (dotCount != null) dotCount!! else currentItemPos) {
         setDotWidth(dots!![i], dotsSize.toInt())
      }
   }

   private fun setUpViewPager() {
      if (viewPager!!.adapter != null) {
         viewPager!!.adapter!!.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
               super.onChanged()
               refreshDots()
            }
         })
      }
   }

   private fun dpToPx(dp: Int): Int {
      return (context.resources.displayMetrics.density * dp).toInt()
   }

   fun setViewPager(viewPager: RecyclerView) {
      this.viewPager = viewPager
      setUpViewPager()
      refreshDots()

//      pagerSnapHelper.attachToRecyclerView(this.viewPager)
   }

   fun setDotCount(count: Int) {
      this.dotCount = count
   }

   companion object {
      private const val DEFAULT_POINT_COLOR = Color.CYAN
      const val DEFAULT_WIDTH_FACTOR = 2.5f
   }
}