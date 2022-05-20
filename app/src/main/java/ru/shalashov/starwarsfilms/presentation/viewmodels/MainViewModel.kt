package ru.shalashov.starwarsfilms.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.shalashov.starwarsfilms.domain.entities.PopularFilms
import ru.shalashov.starwarsfilms.domain.usecases.GetFilmsListUseCase
import ru.shalashov.starwarsfilms.presentation.appState.AppState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFilmsListUseCase: GetFilmsListUseCase
): ViewModel() {

    private val _filmsListLiveData = MutableLiveData<AppState<PopularFilms>>()
    val filmsListLiveData: LiveData<AppState<PopularFilms>> = _filmsListLiveData

    private val handlerList = CoroutineExceptionHandler { _, throwable ->
        _filmsListLiveData.postValue(AppState.Error(throwable.message))
    }

    fun getFilmsList() {
        viewModelScope.launch(handlerList) {
            _filmsListLiveData.value = AppState.Loading
            val filmsList = getFilmsListUseCase()
            _filmsListLiveData.value = filmsList
        }
    }
}