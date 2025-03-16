package com.example.demogetapi.data.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.demogetapi.data.api.ApiService
import com.example.demogetapi.data.model.Post

class PostRepository(private val apiService: ApiService) {
    suspend fun getPosts(): Result<List<Post>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPosts()
                if (response.isSuccessful) {
                    Result.success(response.body() ?: emptyList())
                } else {
                    Result.failure(Exception("Không thể lấy dữ liệu: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}