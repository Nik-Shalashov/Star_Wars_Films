package ru.shalashov.starwarsfilms.domain.repository

import ru.shalashov.starwarsfilms.domain.entities.Credits
import ru.shalashov.starwarsfilms.domain.entities.Details
import ru.shalashov.starwarsfilms.domain.entities.PopularFilms
import ru.shalashov.starwarsfilms.data.appState.AppState

interface Repository {

    suspend fun getFilmsList(): AppState<PopularFilms>
    suspend fun getFilmsDetails(id: Int): AppState<Details>
    suspend fun getCredits(id: Int): AppState<Credits>
}