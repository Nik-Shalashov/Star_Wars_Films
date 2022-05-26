package ru.shalashov.starwarsfilms.data.datasource.localDataSource

import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.FilmsModel
import ru.shalashov.starwarsfilms.domain.entities.Results

fun FilmsModel.toDomainModel() = Results(
    id,
    genre_ids,
    title,
    vote_average,
    poster_path,
    release_date
)