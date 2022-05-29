package ru.shalashov.starwarsfilms.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Credits(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
): Parcelable

data class Cast(
    val name: String,
    val profile_path: String?,
    val character: String
)

data class Crew(
    val name: String,
    val profile_path: String?,
    val job: String
)

