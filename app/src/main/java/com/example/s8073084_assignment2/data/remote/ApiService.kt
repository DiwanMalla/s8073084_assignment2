package com.example.s8073084_assignment2.data.remote

import com.example.s8073084_assignment2.data.DashboardResponse
import com.example.s8073084_assignment2.data.LoginRequest
import com.example.s8073084_assignment2.data.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("{classLocation}/auth")
    suspend fun login(
        @Path("classLocation") classLocation: String,
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("dashboard/{keypass}")
    suspend fun getDashboardData(
        @Path("keypass") keypass: String
    ): DashboardResponse
}