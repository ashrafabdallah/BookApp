package com.example.composebookapp.data.api


import com.example.composebookapp.data.model.BooksModel
import retrofit2.http.GET

interface BooKApi {
    @GET("lists/current/hardcover-fiction.json?api-key=TPhtqFGwkQ4G5EotHbaX1XQkuDB8CQYA")
    suspend fun getBooks(): BooksModel
}