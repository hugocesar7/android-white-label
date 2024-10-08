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

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    init {
        getCountries()
    }

    private fun getCountries() {
        viewModelScope.launch {
            try {
                val result = repository.getAllCountries()
                _countries.value = result
            } catch (e: Exception) {
                // Trate o erro (pode ser com um estado de erro na UI)
            }
        }
    }
}