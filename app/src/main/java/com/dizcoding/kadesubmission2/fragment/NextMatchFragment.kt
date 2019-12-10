package com.dizcoding.kadesubmission2.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dizcoding.kadesubmission2.R
import com.dizcoding.kadesubmission2.adapter.MatchAdapter
import com.dizcoding.kadesubmission2.interfaceclass.FragmentNavigationListener
import com.dizcoding.kadesubmission2.layout.fragment.NextMatchFragmentLayout
import com.dizcoding.kadesubmission2.model.AllTeamResponse
import com.dizcoding.kadesubmission2.model.MatchResponse
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class NextMatchFragment(private val listener: FragmentNavigationListener) : Fragment() {

    private lateinit var adapter: MatchAdapter
    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = NextMatchFragmentLayout().createView(AnkoContext.create(ctx))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        listener.refreshNextMatch(view)
    }

    fun refreshData(dataTeams: AllTeamResponse, data: MatchResponse, view: View) {
        val nextMatchRecyclerView = view.findViewById<RecyclerView>(R.id.nmFrv)
        adapter = MatchAdapter(dataTeams, data, listener)
        nextMatchRecyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        listener.refreshNextMatch(v)
    }
}