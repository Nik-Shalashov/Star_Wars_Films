package ru.shalashov.starwarsfilms.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.shalashov.starwarsfilms.domain.entities.Credits
import ru.shalashov.starwarsfilms.domain.entities.Details
import ru.shalashov.starwarsfilms.domain.entities.PopularFilms

interface FilmsApi {

    @GET("movie/popular")
    suspend fun getFilmsList(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Response<PopularFilms>

    @GET("movie/{id}")
    suspend fun getFilmDetails(
        @Path("id") filmId: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Response<Details>

    @GET("movie/{id}/credits")
    suspend fun getCredits(
        @Path("id") filmId: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Response<Credits>
}