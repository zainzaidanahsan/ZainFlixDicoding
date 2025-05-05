package com.capstone.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id:Int,
    val backdropPath: String,
    val title: String,
    val overview: String,
    val originalTitle: String,
    val releaseDate: String,
    ): Parcelable
