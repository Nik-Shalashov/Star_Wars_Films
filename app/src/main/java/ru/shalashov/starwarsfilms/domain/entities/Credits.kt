package ru.shalashov.starwarsfilms.domain.entities

data class Credits(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)

data class Cast(
    val name: String,
    val profile_path: String?,
    val character: String
)

data class Crew(
    val name: String,
    val profile_path: String?,
    val job: String
)

