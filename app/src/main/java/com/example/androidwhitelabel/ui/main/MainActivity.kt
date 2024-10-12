package com.example.androidwhitelabel.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.androidwhitelabel.R
import com.example.androidwhitelabel.data.model.Country
import com.example.androidwhitelabel.ui.theme.MainTheme
import com.example.androidwhitelabel.viewmodel.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CountriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                Scaffold(
                    topBar = { AppBar() },
                    content = { paddingValues ->
                        val countries = viewModel.countries.observeAsState(initial = emptyList())

                        CountriesList(
                            countries = countries.value,
                            modifier = Modifier.padding(paddingValues)
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.main_screen_app_bar_title),
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun CountriesList(countries: List<Country>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = dimensionResource(id = R.dimen.item_spacing),
                start = dimensionResource(id = R.dimen.list_horizontal_padding),
                bottom = dimensionResource(id = R.dimen.item_spacing),
                end = dimensionResource(id = R.dimen.list_horizontal_padding)
            ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.item_spacing))
    ) {
        items(countries) { country ->
            CountryItem(country = country)
        }
    }
}

@Composable
fun CountryItem(country: Country) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = dimensionResource(id = R.dimen.surface_shadow_elevation),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.row_padding))
        ) {
            Image(
                painter = rememberImagePainter(data = country.flags.png),
                contentDescription = "Flag of ${country.name.common}",
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.image_size))
                    .clip(MaterialTheme.shapes.small)
            )

            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacer_width)))

            Text(
                text = country.name.common,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
