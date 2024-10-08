package com.example.androidwhitelabel.data.repository

import com.example.androidwhitelabel.data.model.Country
import com.example.androidwhitelabel.data.network.RetrofitInstance

class CountriesRepository {
    suspend fun getAllCountries(): List<Country> {
        return RetrofitInstance.api.getAllCountries()
    }
}