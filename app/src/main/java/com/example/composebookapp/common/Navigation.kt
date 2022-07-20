package com.example.composebookapp.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composebookapp.presention.books.BooksListScreen
import com.example.composebookapp.presention.books_details.BooksDetailsScreen


@Composable
fun Navigation(
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.BookListingScreen.route) {

        composable(
            route = Screen.BookListingScreen.route
        ) {
            BooksListScreen(navController = navController)
        }
        composable(route = Screen.BooksDetails.route +
                "?book_title={book_title}," +
                "?book_desc={book_desc}," +
                "?book_url={book_url}," +
                "?book_author={book_author}," +
                "?price={price}," +
                "?publisher={publisher}",
            arguments = listOf(navArgument("book_title") {
                type = NavType.StringType
            }, navArgument("book_desc") {
                type = NavType.StringType
            }, navArgument("book_url") {
                type = NavType.StringType
            }, navArgument("book_author") {
                type = NavType.StringType
            }, navArgument("price") {
                type = NavType.StringType
            }, navArgument("publisher") {
                type = NavType.StringType
            }


            )

        ){

            val bookTitle = it.arguments?.getString("book_title")
            val bookDesc = it.arguments?.getString("book_desc")
            val bookUrl = it.arguments?.getString("book_url")
            val bookAuthor = it.arguments?.getString("book_author")
            val price = it.arguments?.getString("price")
            val publisher = it.arguments?.getString("publisher")

            if (bookTitle != null && bookDesc != null && bookUrl != null && bookAuthor != null && price != null && publisher != null) {
                BooksDetailsScreen(
                    navController,
                    bookTitle = bookTitle,
                    book_desc = bookDesc,
                    book_url = bookUrl,
                    book_author = bookAuthor,
                    price = price,
                    publisher = publisher
                )

            }



        }

    }
}