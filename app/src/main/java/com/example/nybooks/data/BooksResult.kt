package com.example.nybooks.data

import com.example.nybooks.data.model.Book

sealed class BooksResult {
    class Success(val books: List<Book>) : BooksResult()
    class ApiError(val code: Int) : BooksResult()
    class ServerError() : BooksResult()

}