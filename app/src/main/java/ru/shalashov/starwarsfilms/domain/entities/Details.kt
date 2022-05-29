package ru.shalashov.starwarsfilms.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Details(
    val id: Int,
    val title: String,
    val genres: List<Genres>,
    val overview: String?,
    val runtime: Int?,
    val vote_average: Float?,
    val poster_path: String?
): Parcelable
