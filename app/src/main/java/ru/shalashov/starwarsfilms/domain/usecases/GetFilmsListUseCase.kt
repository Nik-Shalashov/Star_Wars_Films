package ru.shalashov.starwarsfilms.domain.usecases

import ru.shalashov.starwarsfilms.domain.repository.Repository
import javax.inject.Inject

class GetFilmsListUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.getFilmsList()
}