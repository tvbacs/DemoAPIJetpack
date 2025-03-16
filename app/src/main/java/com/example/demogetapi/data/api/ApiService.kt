package com.example.demogetapi.data.api
import retrofit2.http.GET
import retrofit2.Response
import com.example.demogetapi.data.model.Post
interface ApiService {
    @GET("post")
    suspend fun getPosts(): Response<List<Post>>
}