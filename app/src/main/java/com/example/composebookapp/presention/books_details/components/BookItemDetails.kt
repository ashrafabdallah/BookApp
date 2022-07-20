package com.example.composebookapp.presention.books_details.components

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.composebookapp.R
import kotlinx.coroutines.launch

@Composable
fun BookItemDetails(
    navController: NavController,
    bookTitle: String,
    book_desc: String,
    book_url: String,
    book_author: String,
    price: String,
    publisher: String

) {
    var scrollState = rememberScrollState()
    var scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState)
        ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(5.dp),
                //  horizontalArrangement = Arrangement.

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier

                        .size(36.dp)
                        .offset(16.dp, 16.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )


            }
            Spacer(modifier = Modifier.height(30.dp))
            items(
                bookTitle = bookTitle,
                book_author = book_author,
                book_desc = book_desc,
                book_url = book_url,
                price = price,
                publisher = publisher
            )
            Spacer(modifier = Modifier.weight(1f))
            BuyButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                onItemClick = {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Under Implementaion", actionLabel = ""
                        )
                    }
                },
                price = price

            )

        }

    }
}

@Composable
fun items(
    bookTitle: String,
    book_desc: String,
    book_url: String,
    book_author: String,
    price: String,
    publisher: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Card(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(180.dp), elevation = 5.dp
            ) {
                Image(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(180.dp)
//                .aspectRatio(1f, matchHeightConstraintsFirst = true)
                        .clip(RoundedCornerShape(10.dp))
                        .border(width = 20.dp, shape = CircleShape, color = Color.Transparent),
                    contentScale = ContentScale.Crop,
                    painter = rememberImagePainter(book_url),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(6f)
            ) {
                Text(
                    text = bookTitle,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold
                )
                Text(
                    text = book_author,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Serif, fontWeight = FontWeight.Normal
                )
            }
        }
        Spacer(Modifier.padding(vertical = 10.dp))
        Text(
            text = "About this book",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Text(
            text = book_desc,
            modifier = Modifier.padding(horizontal = 10.dp),
            fontSize = 16.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Text(
            text = "Published by",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Text(
            text = publisher,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .clickable { },
            fontSize = 16.sp,
            color = Color.Black, textDecoration = TextDecoration.Underline,
        )


    }

}

@Composable
fun BuyButton(modifier: Modifier, price: String, onItemClick: (String) -> Unit) {

    Button(

        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
        // below line is use to add shape for our button.
        shape = MaterialTheme.shapes.medium,
        onClick = {
            onItemClick("")
        }
    ) {
        Text(
            text = "Buy AED ${price}",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
    }
}
