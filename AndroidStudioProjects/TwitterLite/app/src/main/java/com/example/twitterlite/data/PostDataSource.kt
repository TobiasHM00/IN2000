package com.example.twitterlite.data

import com.example.twitterlite.model.Post
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*

class PostDataSource(private val path: String) {
    private val client = HttpClient {
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun fetchPosts(): List<Post> {
        return client.get(path).body()
    }

    /* Orginal
    suspend fun fetchPosts(): List<Post> {
        val posts: List<Post> = client.get(path).body()
        return posts
    }*/
}