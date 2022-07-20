package com.example.composebookapp.presention.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebookapp.domain.usecase.GetBooksUseCase
import com.example.composebookapp.presention.books.BookState
import com.example.composebookapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val getBooksUseCase: GetBooksUseCase):ViewModel(){

    private var getBookJob: Job? = null
    private val _state = mutableStateOf(BookState())
    val state: State<BookState> = _state

    init {
        getBooks()
    }

    private fun getBooks() {
        getBookJob?.cancel()
        getBookJob = getBooksUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = BookState(loading = true)
                }
                is Resource.Success -> {
                    _state.value = BookState(BookList = result.data)
                }
                is Resource.Error -> {
                    _state.value = BookState(error = result.message ?: "Something went wrong!")
                }
            }
        }.launchIn(viewModelScope)
    }
}