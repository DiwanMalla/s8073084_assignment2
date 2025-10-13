package com.example.s8073084_assignment2.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8073084_assignment2.data.LoginRequest
import com.example.s8073084_assignment2.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    // Using StateFlow to represent the UI state
    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginState: StateFlow<LoginUiState> = _loginState

    fun login(classLocation: String, username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginUiState.Loading
            try {
                val loginRequest = LoginRequest(username, password)
                val response = repository.login(classLocation, loginRequest)
                _loginState.value = LoginUiState.Success(response.keypass)
            } catch (e: Exception) {
                _loginState.value = LoginUiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}

// Sealed interface to represent the different states of the login UI
sealed interface LoginUiState {
    object Idle : LoginUiState
    object Loading : LoginUiState
    data class Success(val keypass: String) : LoginUiState
    data class Error(val message: String) : LoginUiState
}
