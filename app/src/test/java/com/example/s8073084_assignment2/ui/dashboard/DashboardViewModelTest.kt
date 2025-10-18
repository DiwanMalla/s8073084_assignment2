package com.example.s8073084_assignment2.ui.dashboard

import com.example.s8073084_assignment2.data.DashboardResponse
import com.example.s8073084_assignment2.data.Entity
import com.example.s8073084_assignment2.data.repository.Repository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DashboardViewModelTest {

    private lateinit var viewModel: DashboardViewModel
    private val repository: Repository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DashboardViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadDashboard success updates state to Success`() = runTest {
        // Given
        val entities = listOf(
            Entity(
                title = "Sample Title",
                author = "Sample Author",
                genre = "Sample Genre",
                publicationYear = 2024,
                description = "Sample Description"
            )
        )
        val dashboardResponse = DashboardResponse(entities, 1)
        coEvery { repository.getDashboardData(any()) } returns dashboardResponse

        // When
        viewModel.loadDashboard("test_key")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val finalState = viewModel.dashboardState.value
        assertEquals(DashboardUiState.Success(entities), finalState)
    }

    @Test
    fun `loadDashboard failure updates state to Error`() = runTest {
        // Given
        val errorMessage = "Network error"
        coEvery { repository.getDashboardData(any()) } throws RuntimeException(errorMessage)

        // When
        viewModel.loadDashboard("test_key")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val finalState = viewModel.dashboardState.value
        assertEquals(DashboardUiState.Error(errorMessage), finalState)
    }
}