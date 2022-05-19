package ru.shalashov.starwarsfilms.domain.repository

import ru.shalashov.starwarsfilms.domain.entities.Credits
import ru.shalashov.starwarsfilms.domain.entities.Details
import ru.shalashov.starwarsfilms.domain.entities.PopularFilms
import ru.shalashov.starwarsfilms.presentation.appState.AppState

interface Repository {

    suspend fun getFilmsList(): AppState<PopularFilms>
    suspend fun getFilmsDetails(id: String): AppState<Details>
    suspend fun getCrews(id: String): AppState<Credits>
}