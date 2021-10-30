package com.example.androiddemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.androiddemo.adapter.CustomAdapter
import com.example.androiddemo.adapter.ViewPagerAdapter


class ActivityMain: AppCompatActivity() {
    var viewPager: ViewPager? = null
    var sliderDotspanel: LinearLayout? = null
    private var dotscount = 0
    private lateinit var dots: Array<ImageView?>
    @ExperimentalStdlibApi
 var data: ArrayList<Model> = arrayListOf()
     var matcheddata: ArrayList<Model> = arrayListOf()
 @ExperimentalStdlibApi
 var adapter: CustomAdapter = CustomAdapter(data)
    lateinit var recyclerView: RecyclerView
  lateinit var searchView: SearchView
    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById<View>(R.id.viewPager) as ViewPager
        sliderDotspanel = findViewById<View>(R.id.SliderDots) as LinearLayout
       recyclerView = findViewById<RecyclerView>(R.id.label_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        searchView = findViewById<SearchView>(R.id.search)
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPager!!.adapter = viewPagerAdapter
        dotscount = viewPagerAdapter.count
        dots = arrayOfNulls(dotscount)
        for (i in 0 until dotscount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.notactive_dote
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            sliderDotspanel!!.addView(dots[i], params)
        }
        dots[0]!!.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.active_dote
            )
        )
        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.notactive_dote
                        )
                    )
                }
                dots[position]!!
                    .setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.active_dote
                        )
                    )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        recyclerView.addItemDecoration(
            SimpleDividerItemDecoration(
                applicationContext,
                R.drawable.divider
            )
        )

        data = ArrayList<Model>()

         data.add(Model(R.drawable.bear, "Bear "))
          data.add(Model(R.drawable.cheetah, "Cheetah "))
          data.add(Model(R.drawable.deer, "Deer "))
           data.add(Model(R.drawable.elephant, "Elephant "))
            data.add(Model(R.drawable.jackal, "Jackal "))
            data.add(Model(R.drawable.rabbit, "Rabbit "))
            data.add(Model(R.drawable.lion, "Lion "))
            data.add(Model(R.drawable.rhinoceros, "Rhinoceros "))
            data.add(Model(R.drawable.fox, "Fox "))
            data.add(Model(R.drawable.tiger, "Tiger "))
            data.add(Model(R.drawable.leopard, "Leopard "))


        adapter = CustomAdapter(data)


        adapter = CustomAdapter(data).also {
           recyclerView.adapter = it
            recyclerView .adapter!!.notifyDataSetChanged()
        }

        val searchIcon = findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK)
      /*  val cancelIcon =findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.BLACK)*/
        val textView = findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.BLACK)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val text = newText
                /*Call filter Method Created in Custom Adapter
                    This Method Filter ListView According to Search Keyword
                 */
                adapter.filter(text)
                if(text.isNullOrEmpty()){


                    searchView.setIconified(true)
                    searchView.clearFocus()


                }
                return false
            }
        })
        searchView.clearFocus()
        val focused = searchView.focusedChild
        focused?.clearFocus()

    }


    class SimpleDividerItemDecoration(context: Context, @DrawableRes dividerRes: Int) : RecyclerView.ItemDecoration() {

        private val mDivider: Drawable = ContextCompat.getDrawable(context, dividerRes)!!

        override fun onDrawOver(c: Canvas, parent: RecyclerView) {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight
            val childCount = parent.childCount
            for (i in 0 until childCount) {
                val child: View = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams
                val top: Int = child.bottom + params.bottomMargin
                val bottom = top + mDivider.intrinsicHeight
                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(c)
            }
        }
    }
}