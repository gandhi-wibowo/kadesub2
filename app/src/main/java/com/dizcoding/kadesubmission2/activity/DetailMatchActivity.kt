package com.dizcoding.kadesubmission2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.dizcoding.kadesubmission2.R
import com.dizcoding.kadesubmission2.api.TheSportDbApi
import com.dizcoding.kadesubmission2.db.dbFavoriteMatch
import com.dizcoding.kadesubmission2.db.table.FavoriteMatch
import com.dizcoding.kadesubmission2.model.AllTeamResponse
import com.dizcoding.kadesubmission2.model.DetailPertandinganResponse
import com.dizcoding.kadesubmission2.model.Event
import com.dizcoding.kadesubmission2.model.Team
import com.dizcoding.kadesubmission2.tools.Utils
import kotlinx.android.synthetic.main.detail_match_activity.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMatchActivity : AppCompatActivity() {
    private var dataDetailPertandingan: DetailPertandinganResponse =
        DetailPertandinganResponse(listOf())
    private var event: Event? = null
    private var home: Team? = null
    private var away: Team? = null
    private var menuItem: Menu? = null

    private var favorit: Boolean = false
    private var isMenuReady: Boolean = false

    private lateinit var idEvent: String
    private lateinit var dataMatch: FavoriteMatch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_match_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        idEvent = intent.getStringExtra("id")
        isFavorit()
        detailPertandingan(idEvent)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_match_menu, menu)
        menuItem = menu
        setIcon()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorit -> {
                if (isMenuReady) {
                    if (favorit) hapus() else simpan()
                    favorit = !favorit
                    setIcon()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setDataMatch(event: Event) {
        dataMatch = FavoriteMatch(
            0, event.dateEvent
            , event.dateEventLocal
            , event.idAwayTeam
            , event.idEvent
            , event.idHomeTeam
            , event.idLeague
            , event.idSoccerXML
            , event.intAwayScore
            , event.intAwayShots
            , event.intHomeScore
            , event.intHomeShots
            , event.intRound
            , event.intSpectators
            , event.strAwayFormation
            , event.strAwayGoalDetails
            , event.strAwayLineupDefense
            , event.strAwayLineupForward
            , event.strAwayLineupGoalkeeper
            , event.strAwayLineupMidfield
            , event.strAwayLineupSubstitutes
            , event.strAwayRedCards
            , event.strAwayTeam
            , event.strAwayYellowCards
            , event.strBanner
            , event.strCircuit
            , event.strCity
            , event.strCountry
            , event.strDate
            , event.strDescriptionEN
            , event.strEvent
            , event.strEventAlternate
            , event.strFanart
            , event.strFilename
            , event.strHomeFormation
            , event.strHomeGoalDetails
            , event.strHomeLineupDefense
            , event.strHomeLineupForward
            , event.strHomeLineupGoalkeeper
            , event.strHomeLineupMidfield
            , event.strHomeLineupSubstitutes
            , event.strHomeRedCards
            , event.strHomeTeam
            , event.strHomeYellowCards
            , event.strLeague
            , event.strLocked
            , event.strMap
            , event.strPoster
            , event.strResult
            , event.strSeason
            , event.strSport
            , event.strTVStation
            , event.strThumb
            , event.strTime
            , event.strTimeLocal
            , event.strTweet1
            , event.strTweet2
            , event.strTweet3
            , event.strVideo
        )
    }

    private fun simpan() {
        dbFavoriteMatch.use {
            insert(
                FavoriteMatch.TABLE_NAME,
                FavoriteMatch.dateEvent to dataMatch.dateEvent,
                FavoriteMatch.dateEventLocal to dataMatch.dateEventLocal,
                FavoriteMatch.idAwayTeam to dataMatch.idAwayTeam,
                FavoriteMatch.idEvent to dataMatch.idEvent,
                FavoriteMatch.idHomeTeam to dataMatch.idHomeTeam,
                FavoriteMatch.idLeague to dataMatch.idLeague,
                FavoriteMatch.idSoccerXML to dataMatch.idSoccerXML,
                FavoriteMatch.intAwayScore to dataMatch.intAwayScore,
                FavoriteMatch.intAwayShots to dataMatch.intAwayShots,
                FavoriteMatch.intHomeScore to dataMatch.intHomeScore,
                FavoriteMatch.intHomeShots to dataMatch.intHomeShots,
                FavoriteMatch.intRound to dataMatch.intRound,
                FavoriteMatch.intSpectators to dataMatch.intSpectators,
                FavoriteMatch.strAwayFormation to dataMatch.strAwayFormation,
                FavoriteMatch.strAwayGoalDetails to dataMatch.strAwayGoalDetails,
                FavoriteMatch.strAwayLineupDefense to dataMatch.strAwayLineupDefense,
                FavoriteMatch.strAwayLineupForward to dataMatch.strAwayLineupForward,
                FavoriteMatch.strAwayLineupGoalkeeper to dataMatch.strAwayLineupGoalkeeper,
                FavoriteMatch.strAwayLineupMidfield to dataMatch.strAwayLineupMidfield,
                FavoriteMatch.strAwayLineupSubstitutes to dataMatch.strAwayLineupSubstitutes,
                FavoriteMatch.strAwayRedCards to dataMatch.strAwayRedCards,
                FavoriteMatch.strAwayTeam to dataMatch.strAwayTeam,
                FavoriteMatch.strAwayYellowCards to dataMatch.strAwayYellowCards,
                FavoriteMatch.strBanner to dataMatch.strBanner,
                FavoriteMatch.strCircuit to dataMatch.strCircuit,
                FavoriteMatch.strCity to dataMatch.strCity,
                FavoriteMatch.strCountry to dataMatch.strCountry,
                FavoriteMatch.strDate to dataMatch.strDate,
                FavoriteMatch.strDescriptionEN to dataMatch.strDescriptionEN,
                FavoriteMatch.strEvent to dataMatch.strEvent,
                FavoriteMatch.strEventAlternate to dataMatch.strEventAlternate,
                FavoriteMatch.strFanart to dataMatch.strFanart,
                FavoriteMatch.strFilename to dataMatch.strFilename,
                FavoriteMatch.strHomeFormation to dataMatch.strHomeFormation,
                FavoriteMatch.strHomeGoalDetails to dataMatch.strHomeGoalDetails,
                FavoriteMatch.strHomeLineupDefense to dataMatch.strHomeLineupDefense,
                FavoriteMatch.strHomeLineupForward to dataMatch.strHomeLineupForward,
                FavoriteMatch.strHomeLineupGoalkeeper to dataMatch.strHomeLineupGoalkeeper,
                FavoriteMatch.strHomeLineupMidfield to dataMatch.strHomeLineupMidfield,
                FavoriteMatch.strHomeLineupSubstitutes to dataMatch.strHomeLineupSubstitutes,
                FavoriteMatch.strHomeRedCards to dataMatch.strHomeRedCards,
                FavoriteMatch.strHomeTeam to dataMatch.strHomeTeam,
                FavoriteMatch.strHomeYellowCards to dataMatch.strHomeYellowCards,
                FavoriteMatch.strLeague to dataMatch.strLeague,
                FavoriteMatch.strLocked to dataMatch.strLocked,
                FavoriteMatch.strMap to dataMatch.strMap,
                FavoriteMatch.strPoster to dataMatch.strPoster,
                FavoriteMatch.strResult to dataMatch.strResult,
                FavoriteMatch.strSeason to dataMatch.strSeason,
                FavoriteMatch.strSport to dataMatch.strSport,
                FavoriteMatch.strTVStation to dataMatch.strTVStation,
                FavoriteMatch.strThumb to dataMatch.strThumb,
                FavoriteMatch.strTime to dataMatch.strTime,
                FavoriteMatch.strTimeLocal to dataMatch.strTimeLocal,
                FavoriteMatch.strTweet1 to dataMatch.strTweet1,
                FavoriteMatch.strTweet2 to dataMatch.strTweet2,
                FavoriteMatch.strTweet3 to dataMatch.strTweet3,
                FavoriteMatch.strVideo to dataMatch.strVideo
            )
        }
    }

    private fun hapus() {
        dbFavoriteMatch.use {
            delete(
                FavoriteMatch.TABLE_NAME,
                "(idEvent = {id})",
                "id" to idEvent
            )
        }
    }

    private fun isFavorit() {
        dbFavoriteMatch.use {
            val result =
                select(FavoriteMatch.TABLE_NAME).whereArgs("idEvent ={id}", "id" to idEvent)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (favorite.isNotEmpty()) favorit = true
        }
    }

    private fun setIcon() {
        if (favorit)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp)
    }

    private fun loadContent() {
        home?.strTeamBadge?.let { Utils().picShow(it, ivHomeBadge) }
        tvHomeScore.text = event?.intHomeScore ?: "-"
        tvHomeName.text = event?.strHomeTeam ?: "-"

        away?.strTeamBadge?.let { Utils().picShow(it, ivAwayBadge) }
        tvAwayScore.text = event?.intAwayScore ?: "-"
        tvAwayName.text = event?.strAwayTeam ?: "-"

        // body header
        strEvent.text = event?.strEvent ?: "-"
        strLeague.text = event?.strLeague ?: "-"

        // home content
        home?.strTeamLogo?.let { Utils().picShow(it, ivHomeLogo) }
        tvHomeTeamName.text = event?.strHomeTeam ?: "-"
        tvHomeFormation.text = event?.strHomeFormation ?: "-"
        tvHomeShots.text = event?.intHomeShots ?: "-"
        tvHomeScoreDetail.text = event?.intHomeScore ?: "-"

        // away content
        away?.strTeamLogo?.let { Utils().picShow(it, ivAwayLogo) }
        tvAwayTeamName.text = event?.strAwayTeam ?: "-"
        tvAwayFormation.text = event?.strAwayFormation ?: "-"
        tvAwayShots.text = event?.intAwayShots ?: "-"
        tvAwayScoreDetail.text = event?.intAwayScore ?: "-"
    }

    private fun detailPertandingan(id_event: String) {
        TheSportDbApi().services.detailPertandingan(id_event).enqueue(object :
            Callback<DetailPertandinganResponse> {
            override fun onFailure(call: Call<DetailPertandinganResponse>, t: Throwable) {
                toast("Failure get detail pertandingan : " + t.message)
            }

            override fun onResponse(
                call: Call<DetailPertandinganResponse>,
                response: Response<DetailPertandinganResponse>
            ) {
                response.body().let {
                    if (it != null) {
                        dataDetailPertandingan = it
                        event = dataDetailPertandingan.events[0]
                        getAllTeams(dataDetailPertandingan.events[0].idLeague)
                    }
                }

            }
        })

    }

    private fun getAllTeams(id_liga: String) {
        TheSportDbApi().services.allTeams(id_liga).enqueue(object : Callback<AllTeamResponse> {
            override fun onFailure(call: Call<AllTeamResponse>, t: Throwable) {
                toast("Failure get cari pertandingan : " + t.message)
            }

            override fun onResponse(
                call: Call<AllTeamResponse>,
                response: Response<AllTeamResponse>
            ) {
                val datas = response.body()
                val event = dataDetailPertandingan.events
                val hme = datas?.teams?.filter { it.idTeam == event[0].idHomeTeam }
                val awy = datas?.teams?.filter { it.idTeam == event[0].idAwayTeam }
                home = hme?.get(0)
                away = awy?.get(0)
                setDataMatch(event[0])
                loadContent()
                isMenuReady = true
                setIcon()
                // kalau sudah sampai sini, baru icon di tampilkan.
            }
        })

    }
}
