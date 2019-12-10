package com.dizcoding.kadesubmission2.fragment


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dizcoding.kadesubmission2.R
import com.dizcoding.kadesubmission2.model.MatchResponse
import androidx.recyclerview.widget.RecyclerView
import com.dizcoding.kadesubmission2.adapter.MatchAdapter
import com.dizcoding.kadesubmission2.interfaceclass.FragmentNavigationListener
import com.dizcoding.kadesubmission2.layout.fragment.PrefMatchFragmentLayout
import com.dizcoding.kadesubmission2.model.AllTeamResponse
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class PrevMatchFragment(private val listener: FragmentNavigationListener) : Fragment() {

    private lateinit var adapter: MatchAdapter
    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = PrefMatchFragmentLayout().createView(AnkoContext.create(ctx))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener.refreshPrevMatch(view)
        v = view
    }

    fun refreshData(dataTeams: AllTeamResponse, data: MatchResponse, view: View) {
        val prefMatchRecyclerView = view.findViewById<RecyclerView>(R.id.pmFrv)
        adapter = MatchAdapter(dataTeams, data, listener)
        prefMatchRecyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        listener.refreshPrevMatch(v)
    }

}
