package ru.shalashov.starwarsfilms.data.datasource.localDataSource.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.shalashov.starwarsfilms.domain.entities.Cast
import ru.shalashov.starwarsfilms.domain.entities.Crew

@Entity(tableName = "credits")
data class CreditsModel(
    @PrimaryKey
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)
