package ru.shalashov.starwarsfilms.domain.entities

data class PopularFilms(
    val results: List<Results>
)

data class Results(
    val id: Int,
    val genre_ids: List<Int>,
    val title: String,
    val vote_average: Float,
    val poster_path: String?
)