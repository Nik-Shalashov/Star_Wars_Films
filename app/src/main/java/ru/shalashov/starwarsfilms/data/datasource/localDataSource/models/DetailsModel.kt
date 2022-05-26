package ru.shalashov.starwarsfilms.data.datasource.localDataSource.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.shalashov.starwarsfilms.domain.entities.Genres

@Entity(
    tableName = "details"/*,
    foreignKeys = [
        ForeignKey(
            entity = ResultsModel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id"),
            onDelete = ForeignKey.CASCADE
        )
    ]*/
)
data class DetailsModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    @Embedded
    val genres: List<GenresModel>,
    val overview: String?,
    val runtime: Int?,
    val vote_average: Float?,
    val poster_path: String?
)

@Entity(
    tableName = "genres",
    foreignKeys = [
        ForeignKey(
            entity = ResultsModel::class,
            parentColumns = arrayOf("genre_ids"),
            childColumns = arrayOf("id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GenresModel(
    @PrimaryKey
    val id: Int,
    val name: String
)
