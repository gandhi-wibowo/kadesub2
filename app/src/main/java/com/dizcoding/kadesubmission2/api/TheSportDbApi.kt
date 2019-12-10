package com.dizcoding.kadesubmission2.api

import android.app.Application
import com.dizcoding.kadesubmission2.BuildConfig
import com.dizcoding.kadesubmission2.BuildConfig.BASE_URL
import com.dizcoding.kadesubmission2.api.apiservice.TheSportDbService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class TheSportDbApi : Application() {
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val services: TheSportDbService = retrofit.create(TheSportDbService::class.java)
}