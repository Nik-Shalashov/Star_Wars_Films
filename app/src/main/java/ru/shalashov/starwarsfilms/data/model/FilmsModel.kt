package ru.shalashov.starwarsfilms.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmsModel(
    @PrimaryKey
    val id: Int,
    val genre_ids: List<Int>,
    val title: String,
    val vote_average: Float,
    val poster_path: String?,
    val release_date: String
)
