package com.dizcoding.kadesubmission2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Leagues(
    val description: String,
    val id: String,
    val logo: String,
    val name: String
) : Parcelable