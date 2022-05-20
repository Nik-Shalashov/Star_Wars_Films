package ru.shalashov.starwarsfilms.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class PopularFilms(
    val results: List<Results>
)

@Parcelize
data class Results(
    val id: Int,
    val genre_ids: List<Int>,
    val title: String,
    val vote_average: Float,
    val poster_path: String?,
    val release_date: String
): Parcelable