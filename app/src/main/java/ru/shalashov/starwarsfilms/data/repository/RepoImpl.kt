package ru.shalashov.starwarsfilms.data.repository

import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import retrofit2.Response
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.database.CreditsDb
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.database.DetailsDb
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.database.FilmsDb
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.toDomainModel
import ru.shalashov.starwarsfilms.data.datasource.remoteDataSource.FilmsDataSource
import ru.shalashov.starwarsfilms.domain.entities.*
import ru.shalashov.starwarsfilms.domain.repository.Repository
import ru.shalashov.starwarsfilms.presentation.appState.AppState
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
            //filmDb.filmsDao().insert()
            return checkResponse(dataSource.getFilmsList())
        } else {
            val filmsInDb = filmDb.filmsDao().getAllFilms()
            val filmsList: MutableList<Results> = mutableListOf()
            for (i in 0..filmsInDb.size) {
                filmsList.add(filmsInDb[i].toDomainModel())
            }
            val popularFilms = PopularFilms(filmsList)
            if (filmsInDb.isNotEmpty()) {
                return AppState.Success(popularFilms)
            } else {
                return AppState.Error("Включите интернет, чтобы получить список фильмов!")
            }
        }
    }

    override suspend fun getFilmsDetails(id: Int): AppState<Details> {
        if (status.isOnline) {
            //detailsDb.detailsDao().insert()
            return checkResponse(dataSource.getFilmsDetails(id))
        } else {
            val detailsInDb = detailsDb.detailsDao().getDetailsById(id)
            val genres: MutableList<Genres> = mutableListOf()
            for (i in 0..detailsInDb.genres.size) {
                genres.add(detailsInDb.genres[i].toDomainModel())
            }
            val filmDetails = detailsInDb.toDomainModel(genres)
            if (detailsInDb.equals(null)) {
                return AppState.Error("Включите интернет, чтобы получить информацию о фильме!")
            } else {
                return AppState.Success(filmDetails)
            }
        }
    }

    override suspend fun getCrews(id: Int): AppState<Credits> = checkResponse(dataSource.getCrews(id))

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