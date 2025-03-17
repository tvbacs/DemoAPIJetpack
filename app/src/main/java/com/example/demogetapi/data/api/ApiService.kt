package com.example.demogetapi.data.api
import com.example.demogetapi.data.model.LoginRequest
import com.example.demogetapi.data.model.LoginResponse
import retrofit2.http.GET
import retrofit2.Response
import com.example.demogetapi.data.model.Post
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @GET("post")
    suspend fun getPosts(): Response<List<Post>>
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}