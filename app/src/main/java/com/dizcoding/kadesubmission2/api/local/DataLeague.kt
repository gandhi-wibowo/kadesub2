package com.dizcoding.kadesubmission2.api.local

import android.content.Context
import com.dizcoding.kadesubmission2.model.Leagues
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import java.io.InputStream

class DataLeague(private val context: Context) {

    private val gson: Gson = Gson()

    private fun data() : String{
        return try {
            val inputStream : InputStream = context.assets.open("league.json")
            val inputString = inputStream.bufferedReader().use{it.readText()}
            inputString
        } catch (e:Exception){
            e.toString()
        }
    }

    fun get() : ArrayList<Leagues>  = gson.fromJson(data())

}