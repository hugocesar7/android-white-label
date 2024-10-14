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

    // LiveData para gerenciar o estado da UI
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    init {
        getCountries()
    }

    private fun getCountries() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading  // Estado de carregamento
            try {
                val countries = repository.getAllCountries()
                _uiState.value = UiState.Success(countries)  // Estado de sucesso
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Erro ao carregar a lista de países")  // Estado de erro
            }
        }
    }
}