package com.example.s8073084_assignment2.data.repository

import com.example.s8073084_assignment2.data.DashboardResponse
import com.example.s8073084_assignment2.data.LoginRequest
import com.example.s8073084_assignment2.data.LoginResponse
import com.example.s8073084_assignment2.data.remote.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {

    suspend fun login(classLocation: String, loginRequest: LoginRequest): LoginResponse {
        return apiService.login(classLocation, loginRequest)
    }

    suspend fun getDashboardData(keypass: String): DashboardResponse {
        return apiService.getDashboardData(keypass)
    }
}