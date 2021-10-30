package com.example.androiddemo.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.androiddemo.ActivityMain
import com.example.androiddemo.R


class ViewPagerAdapter(activityHome: ActivityMain) :PagerAdapter() {


    private var context: Context? = activityHome

    private val images = arrayOf<Int>(
        R.drawable.bear,
        R.drawable.cheetah,
        R.drawable.deer,
        R.drawable.elephant,
        R.drawable.jackal,
        R.drawable.rabbit,
        R.drawable.lion,
        R.drawable.rhinoceros,
        R.drawable.fox,
        R.drawable.tiger,
        R.drawable.leopard


    )




    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.custom_layout, null)
        val imageView = view.findViewById<View>(R.id.imageView) as ImageView
        imageView.setImageResource(images[position])
        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        val vp = container as ViewPager
        vp.removeView(view as View)
    }


}




