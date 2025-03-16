// PostViewModel.kt
package com.example.demogetapi.viewmodel

import com.example.demogetapi.data.model.Post
import com.example.demogetapi.data.repository.PostRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<PostUiState>(PostUiState.Loading)
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = PostUiState.Loading
            repository.getPosts()
                .onSuccess { posts ->
                    _uiState.value = if (posts.isEmpty()) {
                        PostUiState.Empty
                    } else {
                        PostUiState.Success(posts)
                    }
                }
                .onFailure { error ->
                    _uiState.value = PostUiState.Error(error.message ?: "Lỗi không xác định")
                }
        }
    }
}

sealed class PostUiState {
    data object Loading : PostUiState()
    data object Empty : PostUiState()
    data class Success(val posts: List<Post>) : PostUiState()
    data class Error(val message: String) : PostUiState()
}