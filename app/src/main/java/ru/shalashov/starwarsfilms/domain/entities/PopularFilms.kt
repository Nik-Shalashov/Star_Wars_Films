package ru.shalashov.starwarsfilms.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularFilms(
    val results: List<Results>
): Parcelable

data class Results(
    val id: Int,
    val genre_ids: List<Int>,
    val title: String,
    val vote_average: Float,
    val poster_path: String?
)