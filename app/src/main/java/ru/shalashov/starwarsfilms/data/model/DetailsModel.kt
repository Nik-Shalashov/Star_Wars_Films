package ru.shalashov.starwarsfilms.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.shalashov.starwarsfilms.domain.entities.Genres

@Entity(tableName = "details")
data class DetailsModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    val genres: List<Genres>,
    val overview: String?,
    val runtime: Int?,
    val vote_average: Float?,
    val poster_path: String?
)
