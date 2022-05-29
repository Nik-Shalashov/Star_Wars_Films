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

fun Genres.toDataModel() = GenresModel(
    id,
    name
)

fun Results.toDataModel() = ResultsModel(
    id,
    genre_ids,
    title,
    vote_average,
    poster_path,
    release_date
)

fun Details.toDataModel(genresModel: List<GenresModel>) = DetailsModel(
    id,
    title,
    genres = genresModel,
    overview,
    runtime,
    vote_average,
    poster_path
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

fun Cast.toDataModel(id: Int) = CastModel(
    name,
    profile_path,
    character,
    id
)

fun CrewModel.toDomainModel() = Crew(
    name, profile_path, job
)

fun Crew.toDataModel(id: Int) = CrewModel(
    name,
    profile_path,
    job,
    id
)

fun CreditsModel.toDomainModel(cast: List<Cast>, crew: List<Crew>) = Credits(
    id,
    cast = cast,
    crew = crew
)

fun Credits.toDataModel(cast: List<CastModel>, crew: List<CrewModel>) = CreditsModel(
    id,
    cast,
    crew
)