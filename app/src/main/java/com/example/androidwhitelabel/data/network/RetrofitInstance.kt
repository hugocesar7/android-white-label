package com.example.androidwhitelabel.data.network

import com.example.androidwhitelabel.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: CountriesApi by lazy {
        retrofit.create(CountriesApi::class.java)
    }
}