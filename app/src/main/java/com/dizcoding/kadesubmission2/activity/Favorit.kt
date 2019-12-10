package com.dizcoding.kadesubmission2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dizcoding.kadesubmission2.R
import com.dizcoding.kadesubmission2.adapter.MatchAdapter
import com.dizcoding.kadesubmission2.api.TheSportDbApi
import com.dizcoding.kadesubmission2.db.dbFavoriteMatch
import com.dizcoding.kadesubmission2.db.table.FavoriteMatch
import com.dizcoding.kadesubmission2.interfaceclass.FragmentNavigationListener
import com.dizcoding.kadesubmission2.model.*
import kotlinx.android.synthetic.main.activity_favorit.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Favorit : AppCompatActivity(), FragmentNavigationListener {


    private var dataTeams: AllTeamResponse = AllTeamResponse(listOf())
    private var dataPertandingan = MatchResponse(listOf())
    private var listIdLiga = mutableListOf<String>()
    private lateinit var adapter: MatchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorit)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadData()
    }

    private fun loadData() {
        dbFavoriteMatch.use {
            val result = select(FavoriteMatch.TABLE_NAME)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            dataPertandingan = MatchResponse(convertToTeam(favorite))
            addLigaList(dataPertandingan.events)
        }

    }

    private fun convertToTeam(favorite: List<FavoriteMatch>): List<Event> {
        var eventList: MutableList<Event> = mutableListOf()

        favorite.forEach {
            eventList.add(
                Event(
                    it.dateEvent.toString(),
                    it.dateEventLocal.toString(),
                    it.idAwayTeam.toString(),
                    it.idEvent.toString(),
                    it.idHomeTeam.toString(),
                    it.idLeague.toString(),
                    it.idSoccerXML.toString(),
                    it.intAwayScore.toString(),
                    it.intAwayShots.toString(),
                    it.intHomeScore.toString(),
                    it.intHomeShots.toString(),
                    it.intRound.toString(),
                    it.intSpectators.toString(),
                    it.strAwayFormation.toString(),
                    it.strAwayGoalDetails.toString(),
                    it.strAwayLineupDefense.toString(),
                    it.strAwayLineupForward.toString(),
                    it.strAwayLineupGoalkeeper.toString(),
                    it.strAwayLineupMidfield.toString(),
                    it.strAwayLineupSubstitutes.toString(),
                    it.strAwayRedCards.toString(),
                    it.strAwayTeam.toString(),
                    it.strAwayYellowCards.toString(),
                    it.strBanner.toString(),
                    it.strCircuit.toString(),
                    it.strCity.toString(),
                    it.strCountry.toString(),
                    it.strDate.toString(),
                    it.strDescriptionEN.toString(),
                    it.strEvent.toString(),
                    it.strEventAlternate.toString(),
                    it.strFanart.toString(),
                    it.strFilename.toString(),
                    it.strHomeFormation.toString(),
                    it.strHomeGoalDetails.toString(),
                    it.strHomeLineupDefense.toString(),
                    it.strHomeLineupForward.toString(),
                    it.strHomeLineupGoalkeeper.toString(),
                    it.strHomeLineupMidfield.toString(),
                    it.strHomeLineupSubstitutes.toString(),
                    it.strHomeRedCards.toString(),
                    it.strHomeTeam.toString(),
                    it.strHomeYellowCards.toString(),
                    it.strLeague.toString(),
                    it.strLocked.toString(),
                    it.strMap.toString(),
                    it.strPoster.toString(),
                    it.strResult.toString(),
                    it.strSeason.toString(),
                    it.strSport.toString(),
                    it.strTVStation.toString(),
                    it.strThumb.toString(),
                    it.strTime.toString(),
                    it.strTimeLocal.toString(),
                    it.strTweet1.toString(),
                    it.strTweet2.toString(),
                    it.strTweet3.toString(),
                    it.strVideo.toString()
                )
            )
        }


        return eventList
    }

    private fun addLigaList(events: List<Event>) {
        if (!events.isNullOrEmpty()) {
            events.forEach {
                if (cekLigaList(it.idLeague)) {
                    listIdLiga.add(it.idLeague)
                }
            }
        }
        if (!listIdLiga.isNullOrEmpty()) {
            listIdLiga.forEach {
                getAllTeams(it)
            }
        }
    }

    private fun getAllTeams(id_liga: String) {
        TheSportDbApi().services.allTeams(id_liga).enqueue(object : Callback<AllTeamResponse> {
            override fun onFailure(call: Call<AllTeamResponse>, t: Throwable) {
                toast("Failure get All Teams : " + t.message)
            }

            override fun onResponse(
                call: Call<AllTeamResponse>,
                response: Response<AllTeamResponse>
            ) {
                response.body().let {
                    if (it != null) {
                        dataTeams = it
                        refreshData(dataTeams, dataPertandingan)
                    }
                }
            }
        })

    }


    override fun onResume() {
        loadData()
//        refreshData(dataTeams,dataPertandingan)
        super.onResume()
    }


    private fun cekLigaList(idLiga: String): Boolean {
        val listliga = listIdLiga.filter { it == idLiga }
        return listliga.isEmpty()
    }

    private fun refreshData(dataTeams: AllTeamResponse, data: MatchResponse) {
        adapter = MatchAdapter(dataTeams, data, this)
        rvMatchFavorite.adapter = adapter
        rvMatchFavorite.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter.notifyDataSetChanged()
        noData.visibility = View.GONE

    }

    override fun onMatchItemSelected(data: Event) {
        val intent = Intent(applicationContext, DetailMatchActivity::class.java)
        intent.putExtra("id", data.idEvent)
        startActivity(intent)
    }

    override fun openFragment() {}
    override fun loadData(v: View) {}
    override fun refreshNextMatch(v: View) {}
    override fun refreshPrevMatch(v: View) {}
    override fun pagerAdapterDetails(data: Leagues) {}
    override fun pagerAdapterSelected(data: Leagues) {}

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
