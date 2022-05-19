package ru.shalashov.starwarsfilms.domain.entities

data class Details(
    val id: Int,
    val title: String,
    val genres: List<Genres>,
    val overview: String?,
    val runtime: Int?,
    val vote_average: Float?,
    val poster_path: String?
)
