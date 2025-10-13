package com.example.s8073084_assignment2.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8073084_assignment2.data.Entity
import com.example.s8073084_assignment2.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _dashboardState = MutableStateFlow<DashboardUiState>(DashboardUiState.Idle)
    val dashboardState: StateFlow<DashboardUiState> = _dashboardState

    fun loadDashboard(keypass: String) {
        viewModelScope.launch {
            _dashboardState.value = DashboardUiState.Loading
            try {
                val response = repository.getDashboardData(keypass)
                _dashboardState.value = DashboardUiState.Success(response.entities)
            } catch (e: Exception) {
                _dashboardState.value = DashboardUiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}

sealed interface DashboardUiState {
    object Idle : DashboardUiState
    object Loading : DashboardUiState
    data class Success(val entities: List<Entity>) : DashboardUiState
    data class Error(val message: String) : DashboardUiState
}
