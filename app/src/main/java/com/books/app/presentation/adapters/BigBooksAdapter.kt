package com.books.app.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.books.app.databinding.BigBookItemBinding
import com.books.app.databinding.DetailsFragmentBinding
import com.books.domain.entities.Book
import com.squareup.picasso.Picasso

class BigBooksAdapter(private val detailsFragmentBinding: DetailsFragmentBinding) :
    ListAdapter<Book, BigBooksAdapter.BookViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BookViewHolder(
            BigBookItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }

    fun getItemByPosition(position: Int): Book {
        return currentList[position]
    }

    open class BookViewHolder(
        private val binding: BigBookItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        open fun bind(book: Book) {
            binding.apply {
                val coverUrl = book.coverUrl
                title.text = book.name
                author.text = book.author
                Picasso.get().load(coverUrl).into(cover)
            }
        }
    }
}