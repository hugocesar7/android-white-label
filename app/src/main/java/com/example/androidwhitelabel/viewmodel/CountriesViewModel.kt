package com.example.androidwhitelabel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidwhitelabel.data.model.Country
import com.example.androidwhitelabel.data.repository.CountriesRepository
import kotlinx.coroutines.launch

class CountriesViewModel  : ViewModel() {

    private val repository = CountriesRepository()

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchCountries() {
        viewModelScope.launch {
            try {
                val response = repository.getAllCountries()
                _countries.value = response
            } catch (e: Exception) {
                _error.value = "Erro ao carregar países: ${e.message}"
            }
        }
    }
}