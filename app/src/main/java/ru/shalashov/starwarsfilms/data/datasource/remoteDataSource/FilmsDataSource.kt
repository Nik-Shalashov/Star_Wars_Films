package ru.shalashov.starwarsfilms.data.datasource.remoteDataSource

import retrofit2.Response
import ru.shalashov.starwarsfilms.domain.entities.Credits
import ru.shalashov.starwarsfilms.domain.entities.Details
import ru.shalashov.starwarsfilms.domain.entities.PopularFilms

interface FilmsDataSource {

    suspend fun getFilmsList(): Response<PopularFilms>
    suspend fun getFilmsDetails(id: Int): Response<Details>
    suspend fun getCredits(id: Int): Response<Credits>
}