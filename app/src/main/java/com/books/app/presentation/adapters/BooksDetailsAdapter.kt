package com.books.app.presentation.adapters

import androidx.recyclerview.widget.LinearLayoutManager
import com.books.app.databinding.DetailsFragmentBinding
import com.books.app.presentation.fragments.bindInfoAboutBook

class BooksDetailsAdapter(private val detailsBinding: DetailsFragmentBinding) : BooksAdapter() {

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binding.root.setOnClickListener {
            val book = getItem(position)
            detailsBinding.scrollView.scrollTo(0, 0)
            (detailsBinding.bigBookRecycler.layoutManager as LinearLayoutManager).scrollToPosition(
                book.id
            )
            detailsBinding.bindInfoAboutBook(book)
        }
    }
}