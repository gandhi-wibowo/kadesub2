package com.dizcoding.kadesubmission2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dizcoding.kadesubmission2.R
import com.dizcoding.kadesubmission2.adapter.MatchAdapter
import com.dizcoding.kadesubmission2.api.TheSportDbApi
import com.dizcoding.kadesubmission2.interfaceclass.FragmentNavigationListener
import com.dizcoding.kadesubmission2.model.*
import kotlinx.android.synthetic.main.activity_pencarian.*
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_activity.toolbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Pencarian : AppCompatActivity(), SearchView.OnQueryTextListener, FragmentNavigationListener {

    private var dataTeams: AllTeamResponse = AllTeamResponse(listOf())
    private var dataPencarian = MatchResponse(listOf())
    private var listIdLiga = mutableListOf<String>()
    private lateinit var adapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pencarian)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun refreshData(dataTeams: AllTeamResponse, data: MatchResponse) {
        adapter = MatchAdapter(dataTeams, data, this)
        rvMatchSearch.adapter = adapter
        rvMatchSearch.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        val searchItem: MenuItem = menu!!.findItem(R.id.searchMenu)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { toast("Mencari " + it) }
        query?.let { cariPertandingan(it) }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    private fun cariPertandingan(query: String) {
        TheSportDbApi().services.cariPertandingan(query).enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                toast("Failure get cari pertandingan : " + t.message)
            }

            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                var listEvent = response.body()!!.event
                listEvent = if (listEvent.isNullOrEmpty()) {
                    listOf()
                } else {
                    listEvent.filter { it.strSport == "Soccer" }
                }
                dataPencarian = MatchResponse(listEvent)
                addLigaList(dataPencarian.events)
            }
        })

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
                dataTeams = response.body()!!
                if (!dataTeams.teams.isNullOrEmpty()) {
                    if (!dataPencarian.events.isNullOrEmpty()) {
                        noData.visibility = View.GONE
                        refreshData(AllTeamResponse(dataTeams.teams), dataPencarian)
                    }
                }

            }
        })

    }

    private fun cekLigaList(idLiga: String): Boolean {
        val listliga = listIdLiga.filter { it == idLiga }
        return listliga.isEmpty()
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


}
