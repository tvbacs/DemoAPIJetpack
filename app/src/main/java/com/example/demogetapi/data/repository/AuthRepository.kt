package com.example.demogetapi.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.demogetapi.data.api.ApiService
import com.example.demogetapi.data.model.LoginRequest
import com.example.demogetapi.data.model.LoginResponse

class AuthRepository(private val apiService: ApiService) {
    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val loginRequest = LoginRequest(email, password)
                val response = apiService.login(loginRequest)
                if (response.isSuccessful) {
                    Result.success(response.body() ?: throw Exception("Dữ liệu phản hồi trống"))
                } else {
                    Result.failure(Exception("Đăng nhập thất bại: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}