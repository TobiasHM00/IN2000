package com.example.twitterlite.viewmodel

import  androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitterlite.data.PostDataSource
import com.example.twitterlite.data.PostUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    private val dataSource = PostDataSource("https://jsonplaceholder.typicode.com/posts")

    private val _postUiState = MutableStateFlow(PostUiState(posts = listOf()))
    val postUiState: StateFlow<PostUiState> = _postUiState.asStateFlow()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            val posts = dataSource.fetchPosts()
            _postUiState.value = PostUiState(posts = posts)
        }
    }
}