package com.books.app.presentation.adapters

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.books.app.R
import com.books.app.presentation.fragments.MainFragmentDirections
import com.books.domain.entities.Banner
import com.squareup.picasso.Picasso


class BannerAdapter(context: Context, private val banners: List<Banner>) : PagerAdapter() {

    private val mLayoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return banners.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = mLayoutInflater.inflate(R.layout.banner_item, container, false)

        val imageView: ImageView = itemView.findViewById<View>(R.id.banner_image) as ImageView
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        itemView.setOnClickListener {
            val bookId = banners[position].book_id
            val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(bookId)
            it.findNavController().navigate(action)
        }

        Picasso.get().load(banners[position].cover).into(imageView)

        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout?)
    }
}

fun ViewPager.autoScroll(interval: Long) {
    val handler = Handler(Looper.getMainLooper())
    var scrollPosition = 0

    val runnable = object : Runnable {

        override fun run() {
            val count = adapter?.count ?: 0
            setCurrentItem(scrollPosition++ % count, true)
            handler.postDelayed(this, interval)
        }
    }

    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            scrollPosition = position + 1
        }

        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }
    })

    handler.post(runnable)
}