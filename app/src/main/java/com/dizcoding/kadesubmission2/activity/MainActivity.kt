package com.dizcoding.kadesubmission2.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment

import com.dizcoding.kadesubmission2.R
import com.dizcoding.kadesubmission2.api.TheSportDbApi
import com.dizcoding.kadesubmission2.fragment.BaseFragment
import com.dizcoding.kadesubmission2.fragment.NextMatchFragment
import com.dizcoding.kadesubmission2.fragment.PrevMatchFragment
import com.dizcoding.kadesubmission2.interfaceclass.FragmentNavigationListener
import com.dizcoding.kadesubmission2.model.*
import kotlinx.android.synthetic.main.main_activity.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), FragmentNavigationListener {

    private var dataDetailPertandingan: DetailPertandinganResponse =
        DetailPertandinganResponse(listOf())
    private var dataDetailLiga: DetailLigaResponse = DetailLigaResponse(listOf())
    private var dataNextMatch: MatchResponse = MatchResponse(listOf())
    private var dataPrevMatch: MatchResponse = MatchResponse(listOf())
    private var dataTeams: AllTeamResponse = AllTeamResponse(listOf())

    private lateinit var nextMatchFragment: NextMatchFragment
    private lateinit var prefMatchFragment: PrevMatchFragment


    private var idLiga: String = "4328"
    private var idEvent: String = "602262"

    private lateinit var prefMatcView: View
    private lateinit var nextMatcView: View
    private lateinit var conntext: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)
        conntext = this
        nextMatchFragment = NextMatchFragment(this)
        prefMatchFragment = PrevMatchFragment(this)
        dorApi()
        openBaseFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.searchMenu -> startActivity(intentFor<Pencarian>())
            R.id.favorit -> startActivity(intentFor<Favorit>())
        }
        return super.onOptionsItemSelected(item)
    }


    private fun commitFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun openBaseFragment() {
        commitFragment(BaseFragment(supportFragmentManager, this))
    }

    override fun openFragment() {

    }

    override fun pagerAdapterDetails(data: Leagues) {
        val bundle = Bundle()
        bundle.putParcelable("league", data)
        startActivity(intentFor<DetailLigaActivity>("Bundle" to bundle))
    }

    override fun pagerAdapterSelected(data: Leagues) {
        idLiga = data.id
        getAllTeams(idLiga)
        nextMatch(idLiga)
        prevMatch(idLiga)
    }

    override fun refreshNextMatch(v: View) {
        nextMatcView = v
        nextMatch(idLiga)
    }

    override fun refreshPrevMatch(v: View) {
        prefMatcView = v
        prevMatch(idLiga)
    }

    override fun loadData(v: View) {

    }

    override fun onMatchItemSelected(data: Event) {
        val intent = Intent(applicationContext, DetailMatchActivity::class.java)
        intent.putExtra("id", data.idEvent)
        startActivity(intent)
    }

    // Dor API
    private fun dorApi() {
        getAllTeams(idLiga)
        detailLiga(idLiga)
        detailPertandingan(idEvent)
    }

    private fun detailLiga(id_liga: String) {
        TheSportDbApi().services.detailLiga(id_liga).enqueue(object : Callback<DetailLigaResponse> {
            override fun onFailure(call: Call<DetailLigaResponse>, t: Throwable) {
                toast("Failure get Detail Liga : " + t.message)
            }

            override fun onResponse(
                call: Call<DetailLigaResponse>,
                response: Response<DetailLigaResponse>
            ) {
                response.body().let {
                    if (it != null) {
                        dataDetailLiga = it
                    }
                }
            }
        })

    }

    private fun nextMatch(id_liga: String) {
        TheSportDbApi().services.nextMatch(id_liga).enqueue(object : Callback<MatchResponse> {
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                toast("Failure get Next Match : " + t.message)
            }

            override fun onResponse(
                call: Call<MatchResponse>,
                response: Response<MatchResponse>
            ) {
                response.body().let {
                    if (it != null) {
                        dataNextMatch = it
                        nextMatchFragment.refreshData(dataTeams, dataNextMatch, nextMatcView)
                    }
                }
            }
        })

    }

    private fun prevMatch(id_liga: String) {
        TheSportDbApi().services.prevMatch(id_liga).enqueue(object : Callback<MatchResponse> {
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                toast("Failure get Prev Match : " + t.message)
            }

            override fun onResponse(
                call: Call<MatchResponse>,
                response: Response<MatchResponse>
            ) {
                response.body().let {
                    if (it != null) {
                        dataPrevMatch = it
                        prefMatchFragment.refreshData(dataTeams, dataPrevMatch, prefMatcView)
                    }
                }
            }
        })

    }

    private fun detailPertandingan(id_event: String) {
        TheSportDbApi().services.detailPertandingan(id_event)
            .enqueue(object : Callback<DetailPertandinganResponse> {
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
                        }
                    }
                }
            })

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
                dataTeams = AllTeamResponse(listOf())
                response.body().let {
                    if (it != null) {
                        dataTeams = it
                        prefMatchFragment.refreshData(dataTeams, dataPrevMatch, prefMatcView)
                        nextMatchFragment.refreshData(dataTeams, dataNextMatch, nextMatcView)
                    }
                }

            }
        })

    }

}
