package ru.shalashov.starwarsfilms.data.repository

import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import retrofit2.Response
import ru.shalashov.starwarsfilms.data.datasource.remoteDataSource.FilmsDataSource
import ru.shalashov.starwarsfilms.domain.entities.Credits
import ru.shalashov.starwarsfilms.domain.entities.Details
import ru.shalashov.starwarsfilms.domain.entities.PopularFilms
import ru.shalashov.starwarsfilms.domain.repository.Repository
import ru.shalashov.starwarsfilms.presentation.appState.AppState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@BoundTo(Repository::class, SingletonComponent::class)
class RepoImpl @Inject constructor(
    private val dataSource: FilmsDataSource
): Repository {
    override suspend fun getFilmsList(): AppState<PopularFilms> = checkResponse(dataSource.getFilmsList())

    override suspend fun getFilmsDetails(id: String): AppState<Details> = checkResponse(dataSource.getFilmsDetails(id))

    override suspend fun getCrews(id: String): AppState<Credits> = checkResponse(dataSource.getCrews(id))

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