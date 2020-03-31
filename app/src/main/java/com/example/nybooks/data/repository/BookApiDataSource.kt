package com.example.nybooks.data.repository

import com.example.nybooks.data.ApiService
import com.example.nybooks.data.BooksResult
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookApiDataSource : BooksRepository {


    override fun getBooks(bookResultCallback: (result: BooksResult) -> Unit) {
        ApiService.service.getBooks().enqueue(object : Callback<BookBodyResponse> {
            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let { body ->
                            for (result in body.bookResults) {
                                val book = result.bookDEtailResponsResponses[0].getBookModel()
                                books.add(book)
                            }
                        }

                        bookResultCallback(BooksResult.Success(books))
                    }
                    else -> bookResultCallback(BooksResult.ApiError(response.code()))
                }

            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                bookResultCallback(BooksResult.ServerError())
            }
        })
    }

}