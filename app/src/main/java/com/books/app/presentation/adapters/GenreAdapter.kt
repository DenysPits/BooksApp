package com.books.app.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.books.app.databinding.GenreItemBinding

class GenreAdapter :
    ListAdapter<Genre, GenreAdapter.GenreViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem.genre == newItem.genre
            }

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GenreViewHolder(
            GenreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class GenreViewHolder(
        private var binding: GenreItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: Genre) {
            binding.apply {
                this.genre.text = genre.genre
                val adapter = BooksAdapter()
                books.adapter = adapter
                adapter.submitList(genre.books)
            }
        }
    }
}