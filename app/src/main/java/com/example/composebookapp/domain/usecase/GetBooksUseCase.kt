package com.example.composebookapp.domain.usecase


import com.example.composebookapp.data.model.BooksModel
import com.example.composebookapp.domain.reposistory.BookRepository
import com.example.composebookapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    operator fun invoke(): Flow<Resource<BooksModel>> = flow {
        try {
            emit(Resource.Loading<BooksModel>())
            val books = bookRepository.getBooks()
            emit(Resource.Success<BooksModel>(books))
        } catch (http: HttpException) {
            emit(
                Resource.Error<BooksModel>(
                    http.localizedMessage ?: " Something went wrong please try again later!"
                )
            )
        } catch (io: IOException) {
            emit(Resource.Error<BooksModel>("Please check your internet connecion and try again later."))
        }

    }.flowOn(Dispatchers.IO)

}