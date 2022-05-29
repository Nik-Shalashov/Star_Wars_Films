package ru.shalashov.starwarsfilms.data.repository

import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import retrofit2.Response
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.database.CreditsDb
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.database.DetailsDb
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.database.FilmsDb
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.GenresModel
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.toDataModel
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.toDomainModel
import ru.shalashov.starwarsfilms.data.datasource.remoteDataSource.FilmsDataSource
import ru.shalashov.starwarsfilms.domain.entities.*
import ru.shalashov.starwarsfilms.domain.repository.Repository
import ru.shalashov.starwarsfilms.data.appState.AppState
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.CastModel
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.CrewModel
import ru.shalashov.starwarsfilms.utils.AndroidNetworkStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@BoundTo(Repository::class, SingletonComponent::class)
class RepoImpl @Inject constructor(
    private val dataSource: FilmsDataSource,
    private val filmDb: FilmsDb,
    private val detailsDb: DetailsDb,
    private val creditsDb: CreditsDb,
    private val status: AndroidNetworkStatus
): Repository {
    override suspend fun getFilmsList(): AppState<PopularFilms> {
        if (status.isOnline) {
            val filmsFromServer = dataSource.getFilmsList().body()
            for (i in 0..(filmsFromServer?.results?.size ?: 0)) {
                filmsFromServer?.results?.get(i)?.let { filmDb.filmsDao().insert(it.toDataModel()) }
            }
            return checkResponse(dataSource.getFilmsList())
        } else {
            val filmsInDb = filmDb.filmsDao().getAllFilms()
            val filmsList: MutableList<Results> = mutableListOf()
            for (i in 0..filmsInDb.size) {
                filmsList.add(filmsInDb[i].toDomainModel())
            }
            val popularFilms = PopularFilms(filmsList)
            return if (filmsInDb.isNotEmpty()) {
                AppState.Success(popularFilms)
            } else {
                AppState.Error("Включите интернет, чтобы получить список фильмов!")
            }
        }
    }

    override suspend fun getFilmsDetails(id: Int): AppState<Details> {
        if (status.isOnline) {
            val genres = dataSource.getFilmsDetails(id).body()?.genres
            val genresModelForDb: MutableList<GenresModel> = mutableListOf()
            for (i in 0..(genres?.size ?: 0)) {
                genres?.get(i)?.let { genresModelForDb.add(it.toDataModel()) }
            }
            dataSource.getFilmsDetails(id).body()
                ?.let { detailsDb.detailsDao().insert(it.toDataModel(genresModelForDb)) }
            return checkResponse(dataSource.getFilmsDetails(id))
        } else {
            val detailsInDb = detailsDb.detailsDao().getDetailsById(id)
            val genres: MutableList<Genres> = mutableListOf()
            for (i in 0..detailsInDb.genres.size) {
                genres.add(detailsInDb.genres[i].toDomainModel())
            }
            val filmDetails = detailsInDb.toDomainModel(genres)
            return if (detailsInDb.equals(null)) {
                AppState.Error("Включите интернет, чтобы получить информацию о фильме!")
            } else {
                AppState.Success(filmDetails)
            }
        }
    }

    override suspend fun getCredits(id: Int): AppState<Credits> {
        if (status.isOnline) {
            val casts = dataSource.getCredits(id).body()?.cast
            val crews = dataSource.getCredits(id).body()?.crew
            val castModel: MutableList<CastModel> = mutableListOf()
            val crewModel: MutableList<CrewModel> = mutableListOf()
            for (i in 0..(casts?.size ?: 0)) {
                casts?.get(i)?.let { castModel.add(it.toDataModel(id)) }
            }
            for (i in 0..(crews?.size ?: 0)) {
                crews?.get(i)?.let { crewModel.add(it.toDataModel(id)) }
            }
            dataSource.getCredits(id).body()
                ?.let { creditsDb.creditsDao().insert(it.toDataModel(castModel, crewModel)) }
            return checkResponse(dataSource.getCredits(id))
        } else {
            val creditsInDb = creditsDb.creditsDao().getCredits(id)
            val crews: MutableList<Crew> = mutableListOf()
            for (i in 0..creditsInDb.crew.size) {
                crews.add(creditsInDb.crew[i].toDomainModel())
            }
            val casts: MutableList<Cast> = mutableListOf()
            for (i in 0..creditsInDb.cast.size) {
                casts.add(creditsInDb.cast[i].toDomainModel())
            }
            val credits = creditsInDb.toDomainModel(cast = casts, crew = crews)
            return if (creditsInDb.equals(null)) {
                AppState.Error("Включите интернет, чтобы получить список актёров!")
            } else {
                AppState.Success(credits)
            }
        }
    }

    private fun <T> checkResponse(response: Response<T>): AppState<T> {
        return if (response.isSuccessful) AppState.Success(response.body()!!)
        else {
            val message = when (response.code()) {
                401 -> "Invalid API key: You must be granted a valid key!"
                404 -> "The resource you requested could not be found"
                else -> "Unknown error!"
            }
            AppState.Error(message)
        }
    }
}