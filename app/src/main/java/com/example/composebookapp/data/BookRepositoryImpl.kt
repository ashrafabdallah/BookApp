package com.example.composebookapp.data


import com.example.composebookapp.data.api.BooKApi
import com.example.composebookapp.data.model.BooksModel
import com.example.composebookapp.domain.reposistory.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookApi: BooKApi
) : BookRepository {
    override suspend fun getBooks(): BooksModel {
        return bookApi.getBooks()
    }
}