package com.books.app.presentation.adapters

import com.books.app.databinding.DetailsFragmentBinding
import com.books.app.presentation.fragments.bindInfoAboutBook

class BooksDetailsAdapter(private val detailsBinding: DetailsFragmentBinding) : BooksAdapter() {

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binding.root.setOnClickListener {
            val book = getItem(position)
            detailsBinding.bigBookPager.currentItem = book.id
            detailsBinding.scrollView.scrollTo(0, 0)
            detailsBinding.bindInfoAboutBook(book)
        }
    }
}