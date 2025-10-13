package com.example.s8073084_assignment2.ui.login

import com.example.s8073084_assignment2.data.LoginRequest
import com.example.s8073084_assignment2.data.LoginResponse
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
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private val repository: Repository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login success updates state to Success`() = runTest {
        // Given
        val expectedKeypass = "test_key"
        coEvery { repository.login(any(), any()) } returns LoginResponse(expectedKeypass)

        // When
        viewModel.login("footscray", "testuser", "password")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val finalState = viewModel.loginState.value
        assertEquals(LoginUiState.Success(expectedKeypass), finalState)
    }

    @Test
    fun `login failure updates state to Error`() = runTest {
        // Given
        val errorMessage = "Network error"
        coEvery { repository.login(any(), any()) } throws RuntimeException(errorMessage)

        // When
        viewModel.login("footscray", "testuser", "password")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val finalState = viewModel.loginState.value
        assertEquals(LoginUiState.Error(errorMessage), finalState)
    }
}