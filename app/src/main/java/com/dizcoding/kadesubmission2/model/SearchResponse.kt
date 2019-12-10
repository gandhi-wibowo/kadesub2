package com.dizcoding.kadesubmission2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse(
    @SerializedName("event")
    val event: List<Event>
) : Parcelable