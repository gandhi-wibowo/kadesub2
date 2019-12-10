package com.dizcoding.kadesubmission2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AllTeamResponse(
    @SerializedName("teams")
    val teams: List<Team>
) : Parcelable