package ru.shalashov.starwarsfilms.data.datasource.localDataSource

import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.*
import ru.shalashov.starwarsfilms.domain.entities.*

fun ResultsModel.toDomainModel() = Results(
    id,
    genre_ids,
    title,
    vote_average,
    poster_path,
    release_date
)

fun GenresModel.toDomainModel() = Genres(
    id,
    name
)

fun PopularFilmsModel.toDomainModel(resultsModel: ResultsModel) = PopularFilms(
    listOf(resultsModel.toDomainModel())
)

fun DetailsModel.toDomainModel(genres: List<Genres>) = Details(
    id,
    title,
    genres = genres,
    overview,
    runtime,
    vote_average,
    poster_path
)

fun CastModel.toDomainModel() = Cast(
    name, profile_path, character
)

fun CrewModel.toDomainModel() = Crew(
    name, profile_path, job
)

fun CreditsModel.toDomainModel(castModel: CastModel, crewModel: CrewModel) = Credits(
    id,
    cast = listOf(castModel.toDomainModel()),
    crew = listOf(crewModel.toDomainModel())
)