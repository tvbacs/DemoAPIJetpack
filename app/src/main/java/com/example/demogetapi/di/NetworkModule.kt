package com.example.demogetapi.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import com.example.demogetapi.data.api.ApiService
import com.example.demogetapi.data.repository.PostRepository
import com.example.demogetapi.data.repository.AuthRepository
import android.content.Context
import com.example.demogetapi.data.local.UserPreferencesRepository

object NetworkModule {
    private const val BASE_URL = "http://192.168.1.12:5000/"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val contentType = "application/json".toMediaType()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
    val postRepository = PostRepository(apiService)
    val authRepository = AuthRepository(apiService)

    private var userPreferencesRepository: UserPreferencesRepository? = null

    fun provideUserPreferencesRepository(context: Context): UserPreferencesRepository {
        return userPreferencesRepository ?: UserPreferencesRepository(context).also {
            userPreferencesRepository = it
        }
    }
}