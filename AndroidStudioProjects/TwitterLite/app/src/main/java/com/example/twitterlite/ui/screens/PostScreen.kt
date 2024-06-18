package com.example.twitterlite.ui.screens

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.twitterlite.model.Post
import com.example.twitterlite.ui.components.PostCard
import com.example.twitterlite.viewmodel.PostsViewModel

@Composable
fun PostScreen(postsViewModel: PostsViewModel = viewModel()) {
    val postUiState by postsViewModel.postUiState.collectAsState()

    Log.d("PostUiState", postUiState.posts.toString())

    PostsView(postUiState.posts)
}

@Composable
fun PostsView(posts: List<Post>) {
    LazyColumn {
        items(posts) { postData ->
            PostCard(postData = postData)
        }
    }
}