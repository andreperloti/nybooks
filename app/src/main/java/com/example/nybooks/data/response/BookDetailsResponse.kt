package com.example.nybooks.data.response

import com.example.nybooks.data.model.Book
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class BookDetailsResponse(
    val title :String,
    val author : String,
    val description: String
){
    fun getBookModel() = Book(
        title = this.title,
        author = this.author,
        description = this.description
    )

}