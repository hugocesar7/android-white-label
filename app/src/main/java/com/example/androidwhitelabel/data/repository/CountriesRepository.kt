package com.example.androidwhitelabel.data.repository

import com.example.androidwhitelabel.data.model.Country
import com.example.androidwhitelabel.data.network.CountriesApi

class CountriesRepository(private val api: CountriesApi) {
    suspend fun getAllCountries(): List<Country> {
        return api.getAllCountries()
    }
}