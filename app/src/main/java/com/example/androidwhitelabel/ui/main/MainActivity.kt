package com.example.androidwhitelabel.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidwhitelabel.data.model.Country
import com.example.androidwhitelabel.viewmodel.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CountriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val countries = viewModel.countries.observeAsState(initial = emptyList())
            CountriesList(countries = countries.value)
        }
    }
}

@Composable
fun CountriesList(countries: List<Country>) {
    LazyColumn {
        items(countries) { country ->
            Text(text = country.name.common)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCountriesList() {
    CountriesList(countries = listOf())
}