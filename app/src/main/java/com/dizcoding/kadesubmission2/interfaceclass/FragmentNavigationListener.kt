package com.dizcoding.kadesubmission2.interfaceclass


import android.view.View
import com.dizcoding.kadesubmission2.model.Event
import com.dizcoding.kadesubmission2.model.Leagues

interface FragmentNavigationListener {

    fun onMatchItemSelected(data: Event)

    fun openFragment()
    fun loadData(v: View)

    fun refreshNextMatch(v: View)
    fun refreshPrevMatch(v: View)

    fun pagerAdapterDetails(data: Leagues)
    fun pagerAdapterSelected(data: Leagues)

}