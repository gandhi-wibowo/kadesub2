package com.dizcoding.kadesubmission2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dizcoding.kadesubmission2.model.Flist

class ViewPagerNextPrevAdapter(fm: FragmentManager, val flist: List<Flist> = listOf()) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = flist[position].fragment

    override fun getCount(): Int = flist.size

    override fun getPageTitle(position: Int): CharSequence? = flist[position].title
}