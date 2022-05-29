package ru.shalashov.starwarsfilms.data.datasource.remoteDataSource

import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import retrofit2.Response
import ru.shalashov.starwarsfilms.data.network.FilmsApi
import ru.shalashov.starwarsfilms.domain.entities.Credits
import ru.shalashov.starwarsfilms.domain.entities.Details
import ru.shalashov.starwarsfilms.domain.entities.PopularFilms
import javax.inject.Inject
import javax.inject.Singleton

const val apiKey = "14d8ed808b564c91f81edffb7d01a16e"
const val language = "en-US"

@Singleton
@BoundTo(FilmsDataSource::class, SingletonComponent::class)
class FilmsRemoteDataSource @Inject constructor(
    private val api: FilmsApi
): FilmsDataSource {

    override suspend fun getFilmsList(): Response<PopularFilms> = api.getFilmsList(apiKey, language)

    override suspend fun getFilmsDetails(id: Int): Response<Details> = api.getFilmDetails(id, apiKey, language)

    override suspend fun getCredits(id: Int): Response<Credits> = api.getCredits(id, apiKey, language)
}