package com.books.app.presentation.adapters

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.books.app.databinding.BookItemBinding
import com.squareup.picasso.Picasso

class BooksAdapter :
    ListAdapter<Book, BooksAdapter.BookViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.cover == newItem.cover
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BookViewHolder(
            BookItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = getItem(position)
        val marginInPx = 16f
        val marginInDp = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            marginInPx,
            holder.itemView.context.resources.displayMetrics
        )
        if (position == itemCount - 1) {
            val marginLayoutParams =
                holder.binding.bookContainer.layoutParams as ViewGroup.MarginLayoutParams
            marginLayoutParams.marginEnd = marginInDp.toInt()
        }
        holder.bind(item)
    }

    class BookViewHolder(
        val binding: BookItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.apply {
                val coverUrl = book.cover
                title.text = book.title
                Picasso.get().load(coverUrl).into(cover)
            }
        }
    }
}