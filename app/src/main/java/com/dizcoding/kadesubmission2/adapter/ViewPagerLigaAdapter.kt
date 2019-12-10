package com.dizcoding.kadesubmission2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.dizcoding.kadesubmission2.R
import com.dizcoding.kadesubmission2.model.Leagues
import androidx.viewpager.widget.ViewPager
import com.dizcoding.kadesubmission2.interfaceclass.FragmentNavigationListener
import com.dizcoding.kadesubmission2.tools.Utils


class ViewPagerLigaAdapter(
    val ctx: Context,
    val data: ArrayList<Leagues> = arrayListOf(),
    var listener: FragmentNavigationListener
) : PagerAdapter() {

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int = data.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.liga_item, null)

        // inisialisasi

        val imView = view.findViewById<ImageView>(R.id.ivPagerAdapter)
        val tvLigaName = view.findViewById<TextView>(R.id.tvLigaName)
        val tvDetailLiga = view.findViewById<TextView>(R.id.tvDetailLiga)

        // listener
        tvDetailLiga.setOnClickListener { listener.pagerAdapterDetails(data[position]) } // open detail
        view.setOnClickListener { listener.pagerAdapterSelected(data[position]) } // refresh liga

        // setdata
        Utils().picShow("file:///android_asset/" + data[position].logo, imView)
        tvLigaName.text = data[position].name

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

}