package com.dizcoding.kadesubmission2.api.apiservice

import com.dizcoding.kadesubmission2.BuildConfig.*
import com.dizcoding.kadesubmission2.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheSportDbService {

    @GET(DETAIL_LIGA)
    fun detailLiga(@Query("id") id_liga: String): Call<DetailLigaResponse>

    @GET(NEXT_MATCH)
    fun nextMatch(@Query("id") id_liga: String): Call<MatchResponse>

    @GET(PREV_MATCH)
    fun prevMatch(@Query("id") id_liga: String): Call<MatchResponse>

    @GET(DETAIL_PERTANDINGAN)
    fun detailPertandingan(@Query("id") id_event: String): Call<DetailPertandinganResponse>

    @GET(CARI_PERTANDINGAN)
    fun cariPertandingan(@Query("e") query: String): Call<SearchResponse>

    @GET(ALL_TEAM_BY_IDLIGA)
    fun allTeams(@Query("id") id_liga: String): Call<AllTeamResponse>

}