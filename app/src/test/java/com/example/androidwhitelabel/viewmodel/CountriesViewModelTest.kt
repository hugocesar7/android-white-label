package com.example.androidwhitelabel.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.androidwhitelabel.data.model.Country
import com.example.androidwhitelabel.data.model.Flags
import com.example.androidwhitelabel.data.model.Name
import com.example.androidwhitelabel.data.repository.CountriesRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CountriesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CountriesViewModel
    private val repository: CountriesRepository = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CountriesViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should emit Loading then Success when getCountries succeeds`() = runTest {
        // Arrange
        val countryList = listOf(
            Country(
                name = Name(common = "Brazil"),
                capital = listOf("Brasília"),
                population = 213_000_000,
                flags = Flags(png = "https://flagcdn.com/w320/br.png")
            ),
            Country(
                name = Name(common = "Canada"),
                capital = listOf("Ottawa"),
                population = 37_590_000,
                flags = Flags(png = "https://flagcdn.com/w320/ca.png")
            )
        )
        coEvery { repository.getAllCountries() } returns countryList

        val observer = mockk<Observer<UiState>>(relaxed = true)
        viewModel.uiState.observeForever(observer)

        // Act
        viewModel.loadCountries()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        verify { observer.onChanged(UiState.Loading) }
        verify { observer.onChanged(UiState.Success(countryList)) }
    }

    @Test
    fun `should emit Loading then Error when getCountries fails`() = runTest {
        // Arrange
        coEvery { repository.getAllCountries() } throws Exception("Network Error")

        val observer = mockk<Observer<UiState>>(relaxed = true)
        viewModel.uiState.observeForever(observer)

        // Act
        viewModel.loadCountries()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        verify { observer.onChanged(UiState.Loading) }
        verify { observer.onChanged(UiState.Error("Erro ao carregar a lista de países")) }
    }
}
