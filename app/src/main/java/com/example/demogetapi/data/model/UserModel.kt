package com.example.demogetapi.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val user_id: String,
    val fullname: String,
    val email: String,
    val username: String,
    val password: String,
    val phonenumber: String,
    val gender: String,
    val country: String?,
    val nickname: String,
    val email_verified_at: String?,
    val avatar: String?,
    val birthday: String,
    val type: String,
    val otp: String?,
    val otp_expires: String,
    val status: String,
    val level: Int,
    val createdAt: String,
    val updatedAt: String
)
