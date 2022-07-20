package com.example.composebookapp.presention.books_details

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.composebookapp.presention.books_details.components.BookItemDetails

@Composable
fun BooksDetailsScreen(
    navController: NavHostController,
    bookTitle: String,
    book_desc: String,
    book_url: String,
    book_author: String,
    price: String,
    publisher: String
) {
    BookItemDetails(navController,
    bookTitle = bookTitle,
        price = price,
        publisher = publisher, book_url = book_url,
        book_desc = book_desc,
        book_author = book_author
        )
}