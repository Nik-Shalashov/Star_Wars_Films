package ru.shalashov.starwarsfilms.data.datasource.localDataSource.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.shalashov.starwarsfilms.domain.entities.Cast
import ru.shalashov.starwarsfilms.domain.entities.Crew

@Entity(
    tableName = "credits",
    foreignKeys = [
        ForeignKey(
            entity = DetailsModel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CreditsModel(
    @PrimaryKey
    val id: Int,
    @Embedded
    val cast: List<CastModel>,
    @Embedded
    val crew: List<CrewModel>
)

@Entity(
    tableName = "cast",
    foreignKeys = [
        ForeignKey(
            entity = CreditsModel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("creditsId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CastModel(
    @PrimaryKey
    val name: String,
    val profile_path: String?,
    val character: String,
    val creditsId: Int
)

@Entity(
    tableName = "crew",
    foreignKeys = [
        ForeignKey(
            entity = CreditsModel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("creditsId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CrewModel(
    @PrimaryKey
    val name: String,
    val profile_path: String?,
    val job: String,
    val creditsId: Int
)
