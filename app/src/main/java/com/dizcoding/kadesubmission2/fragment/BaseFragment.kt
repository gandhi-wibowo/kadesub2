package com.dizcoding.kadesubmission2.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.dizcoding.kadesubmission2.R
import com.dizcoding.kadesubmission2.adapter.ViewPagerLigaAdapter
import com.dizcoding.kadesubmission2.adapter.ViewPagerNextPrevAdapter
import com.dizcoding.kadesubmission2.api.local.DataLeague
import com.dizcoding.kadesubmission2.interfaceclass.FragmentNavigationListener
import com.dizcoding.kadesubmission2.layout.fragment.BaseFragmentLayout
import com.dizcoding.kadesubmission2.model.AllTeamResponse
import com.dizcoding.kadesubmission2.model.Flist
import com.dizcoding.kadesubmission2.model.MatchResponse
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class BaseFragment(
    var fm: FragmentManager,
    var listener: FragmentNavigationListener
) : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var ligaViewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = BaseFragmentLayout().createView(AnkoContext.create(ctx))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = view.findViewById(R.id.bfIdtbl)
        viewPager = view.findViewById(R.id.bfVp)
        ligaViewPager = view.findViewById(R.id.lgVp)

        val flist: List<Flist> = listOf(
            Flist("Prev Match", PrevMatchFragment(listener)),
            Flist("Next Match", NextMatchFragment(listener))
        )
        viewPager.adapter = ViewPagerNextPrevAdapter(fm, flist)
        tabLayout.setupWithViewPager(viewPager)
        ligaViewPager.adapter = ViewPagerLigaAdapter(ctx, DataLeague(ctx).get(), listener)
    }

}
