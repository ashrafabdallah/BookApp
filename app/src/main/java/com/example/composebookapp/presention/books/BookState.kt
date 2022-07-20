package com.example.composebookapp.presention.books

import com.example.composebookapp.data.model.BooksModel

data class BookState(
    val error: String = "",
    val success: Boolean = false,
    val loading: Boolean = false,
    val BookList: BooksModel? = null
)
