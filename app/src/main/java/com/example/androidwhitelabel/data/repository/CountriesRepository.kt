package com.example.androidwhitelabel.data.repository

import com.example.androidwhitelabel.BuildConfig
import com.example.androidwhitelabel.data.model.Country
import com.example.androidwhitelabel.data.network.CountriesApi

class CountriesRepository(private val api: CountriesApi) {
    suspend fun getAllCountries(): List<Country> {
        if (BuildConfig.USE_MOCK) {
            var aaa = "aaa"
        } else {
            var bbb = "bbb"
        }

        return api.getAllCountries()
    }
}