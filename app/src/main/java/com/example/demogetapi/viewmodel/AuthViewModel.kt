package com.example.demogetapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.demogetapi.data.repository.AuthRepository
import com.example.demogetapi.data.local.UserPreferencesRepository
import com.example.demogetapi.data.model.LoginResponse
import kotlinx.coroutines.flow.first

class AuthViewModel(
    private val authRepository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Initial)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    init {
        checkAuthState()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            authRepository.login(email, password)
                .onSuccess { response ->
                    userPreferencesRepository.saveAuthToken(response.token)
                    _uiState.value = AuthUiState.Success(response)
                }
                .onFailure { error ->
                    _uiState.value = AuthUiState.Error(error.message ?: "Đăng nhập thất bại")
                }
        }
    }

    fun checkAuthState() {
        viewModelScope.launch {
            val isLoggedIn = userPreferencesRepository.isLoggedIn().first()
            if (isLoggedIn) {
                _uiState.value = AuthUiState.LoggedIn
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferencesRepository.clearAuthToken()
            _uiState.value = AuthUiState.Initial
        }
    }
}

sealed class AuthUiState {
    data object Initial : AuthUiState()
    data object Loading : AuthUiState()
    data object LoggedIn : AuthUiState()
    data class Success(val response: LoginResponse) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}