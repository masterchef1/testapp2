package com.mac.propertymaster

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    private lateinit var layouts: IntArray
    lateinit var onBoardingPagerAdapter: OnBoardingPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.view_pager)
        onBoardingPagerAdapter = OnBoardingPagerAdapter(viewPager)
        layouts = intArrayOf(
            R.layout.first_offer,
            R.layout.second_offer,
            R.layout.third_offer
        )
        onBoardingPagerAdapter.addItems(layouts)
        viewPager.adapter = onBoardingPagerAdapter
    }

    class OnBoardingPagerAdapter(val pager: ViewPager) : PagerAdapter() {
        lateinit var screensArray: IntArray

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = LayoutInflater.from(container.context)
                .inflate(screensArray[position], container, false)

            val next = view.findViewById<TextView>(R.id.next)
            val back = view.findViewById<ImageView>(R.id.back)

            next.setOnClickListener {
                if (position < 2) {
                    pager.currentItem = position + 1
                } else {
                    pager.context.startActivity(Intent(pager.context, SecondActivity::class.java))
                }
            }

            back.setOnClickListener {
                pager.currentItem = position - 1
            }

            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            if (screensArray != null) {
                return screensArray.size
            } else return 0
        }

        fun addItems(screenArray: IntArray) {
            this.screensArray = screenArray
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }

    class DepthPageTransformer : ViewPager.PageTransformer {
        private val MIN_SCALE = 0.5f

        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 0 -> { // [-1,0]
                        // Use the default slide transition when moving to the left page
                        alpha = 1f
                        translationX = 0f
                        scaleX = 1f
                        scaleY = 1f
                    }
                    position <= 1 -> { // (0,1]
                        // Fade the page out.
                        alpha = 1 - position

                        // Counteract the default slide transition
                        translationX = pageWidth * -position

                        // Scale the page down (between MIN_SCALE and 1)
                        val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position)))
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }
}