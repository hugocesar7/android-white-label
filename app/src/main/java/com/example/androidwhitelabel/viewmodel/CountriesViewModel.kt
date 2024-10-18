package com.example.androidwhitelabel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidwhitelabel.data.model.Country
import com.example.androidwhitelabel.data.repository.CountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val repository: CountriesRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun loadCountries() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val countries = repository.getAllCountries()
                _uiState.value = UiState.Success(countries)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Erro ao carregar a lista de países")
            }
        }
    }
}