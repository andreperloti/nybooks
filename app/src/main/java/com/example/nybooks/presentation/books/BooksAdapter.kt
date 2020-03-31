package com.example.nybooks.presentation.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.R
import com.example.nybooks.data.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BooksAdapter(
    val books: List<Book>,
    val onItemClickListener: ((book: Book) -> Unit)
) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_book,
                parent,
                false
            ), onItemClickListener
        )
    }

    override fun getItemCount() = books.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bondView(books[position])
    }

    class ViewHolder(itemView: View, private val onItemClickListener: ((book: Book) -> Unit)) :
        RecyclerView.ViewHolder(itemView) {

        val title = itemView.tvTitle
        val author = itemView.tvAuthor

        fun bondView(book: Book) {
            title.text = book.title
            author.text = book.author


            itemView.setOnClickListener {
                onItemClickListener.invoke(book)
            }
        }


    }

}