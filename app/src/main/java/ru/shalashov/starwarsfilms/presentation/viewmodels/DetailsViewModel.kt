package ru.shalashov.starwarsfilms.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.shalashov.starwarsfilms.domain.entities.Credits
import ru.shalashov.starwarsfilms.domain.entities.Details
import ru.shalashov.starwarsfilms.domain.usecases.GetCrewsUseCase
import ru.shalashov.starwarsfilms.domain.usecases.GetFilmDetailsUseCase
import ru.shalashov.starwarsfilms.data.appState.AppState
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getFilmDetailsUseCase: GetFilmDetailsUseCase,
    private val getCrewsUseCase: GetCrewsUseCase
): ViewModel() {

    private val _detailsLiveData = MutableLiveData<AppState<Details>>()
    val detailsLiveData: LiveData<AppState<Details>> = _detailsLiveData

    private val handlerDetails = CoroutineExceptionHandler { _, throwable ->
        _detailsLiveData.postValue(AppState.Error(throwable.message))
    }

    private val _creditsLiveData = MutableLiveData<AppState<Credits>>()
    val creditsLiveData: LiveData<AppState<Credits>> = _creditsLiveData

    private val handlerCredits = CoroutineExceptionHandler { _, throwable ->
        _creditsLiveData.postValue(AppState.Error(throwable.message))
    }

    fun getDetails(id: Int) {
        viewModelScope.launch(handlerDetails) {
            _detailsLiveData.value = AppState.Loading
            val details = getFilmDetailsUseCase(id)
            _detailsLiveData.value = details
        }
    }

    fun getCredits(id: Int) {
        viewModelScope.launch(handlerCredits) {
            _creditsLiveData.value = AppState.Loading
            val credits = getCrewsUseCase(id)
            _creditsLiveData.value = credits
        }
    }
}