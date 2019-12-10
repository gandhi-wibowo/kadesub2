package com.dizcoding.kadesubmission2.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dizcoding.kadesubmission2.R
import com.dizcoding.kadesubmission2.activity.DetailMatchActivity
import com.dizcoding.kadesubmission2.interfaceclass.FragmentNavigationListener
import com.dizcoding.kadesubmission2.layout.adapter.MatchItemLayout
import com.dizcoding.kadesubmission2.model.AllTeamResponse
import com.dizcoding.kadesubmission2.model.Event
import com.dizcoding.kadesubmission2.model.MatchResponse
import com.dizcoding.kadesubmission2.tools.Utils
import org.jetbrains.anko.AnkoContext

class MatchAdapter(
    var dataTeams: AllTeamResponse = AllTeamResponse(
        listOf()
    ),
    var data: MatchResponse = MatchResponse(
        listOf()
    ),
    val listener: FragmentNavigationListener
) : RecyclerView.Adapter<MatchAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(MatchItemLayout().createView(AnkoContext.Companion.create(parent.context)))

    override fun getItemCount(): Int = data.events.size
    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(data.events[position])

    inner class Holder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val ivHome = itemview.findViewById<ImageView>(R.id.ivHome)
        val ivAway = itemview.findViewById<ImageView>(R.id.ivAway)
        val tvHomeScore = itemview.findViewById<TextView>(R.id.tvHomeScore)
        val tvAwayScore = itemview.findViewById<TextView>(R.id.tvAwayScore)
        val tvHomeTeamName = itemview.findViewById<TextView>(R.id.tvHomeTeamName)
        val tvAwayTeamName = itemview.findViewById<TextView>(R.id.tvAwayTeamName)

        val tvLigaName = itemview.findViewById<TextView>(R.id.tvLigaName)
        val tvEventName = itemview.findViewById<TextView>(R.id.tvEventName)

        fun bind(event: Event) {
            val listTeams = dataTeams.teams
            val homeTeam = listTeams.filter { it.idTeam == event.idHomeTeam }
            val awayTeam = listTeams.filter { it.idTeam == event.idAwayTeam }

            if (listTeams.isNotEmpty()) {
                if (awayTeam.isNotEmpty()) {
                    Utils().picShow(awayTeam[0].strTeamBadge, ivAway)
                }
                if (homeTeam.isNotEmpty()) {
                    Utils().picShow(homeTeam[0].strTeamBadge, ivHome)
                }
            }
            if (event.intHomeScore.isNullOrEmpty() || event.intHomeScore == "null") {
                tvHomeScore.text = "-"
            } else {
                tvHomeScore.text = event.intHomeScore
            }
            if (event.intAwayScore.isNullOrEmpty() || event.intAwayScore == "null") {
                tvAwayScore.text = "-"
            } else {
                tvAwayScore.text = event.intAwayScore
            }

            tvHomeTeamName.text = event.strHomeTeam
            tvAwayTeamName.text = event.strAwayTeam
            tvLigaName.text = event.strLeague
            tvEventName.text = event.strEvent

            itemView.setOnClickListener { listener.onMatchItemSelected(event) }
        }
    }

}