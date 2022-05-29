package ru.shalashov.starwarsfilms.data.datasource.localDataSource.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmsList")
data class PopularFilmsModel(
    @Embedded
    val results: List<ResultsModel>
)

@Entity(tableName = "films")
data class ResultsModel(
    @PrimaryKey
    val id: Int,
    val genre_ids: List<Int>,
    val title: String,
    val vote_average: Float,
    val poster_path: String?,
    val release_date: String
)
