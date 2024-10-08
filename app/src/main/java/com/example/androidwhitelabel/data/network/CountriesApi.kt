package com.example.androidwhitelabel.data.network

import com.example.androidwhitelabel.data.model.Country
import retrofit2.http.GET

interface CountriesApi {
    @GET("v3.1/all")
    suspend fun getAllCountries(): List<Country>
}