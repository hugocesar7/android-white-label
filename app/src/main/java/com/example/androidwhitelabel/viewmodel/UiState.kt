package com.example.androidwhitelabel.viewmodel

import com.example.androidwhitelabel.data.model.Country

sealed class UiState {
    object Loading : UiState()
    data class Success(val countries: List<Country>) : UiState()
    data class Error(val message: String) : UiState()
}