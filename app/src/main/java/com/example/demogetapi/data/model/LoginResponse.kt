package com.example.demogetapi.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val message: String,
    val token: String
)