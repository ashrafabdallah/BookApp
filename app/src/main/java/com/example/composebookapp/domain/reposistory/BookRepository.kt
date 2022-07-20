package com.example.composebookapp.domain.reposistory


import com.example.composebookapp.data.model.BooksModel

interface BookRepository {
    suspend fun getBooks(): BooksModel
}