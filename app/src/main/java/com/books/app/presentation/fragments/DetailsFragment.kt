package com.books.app.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.books.app.databinding.DetailsFragmentBinding
import com.books.app.presentation.BooksApplication
import com.books.app.presentation.adapters.BigBooksAdapter
import com.books.app.presentation.adapters.BooksDetailsAdapter
import com.books.app.presentation.viewmodels.DetailsViewModel
import com.books.domain.entities.Book
import javax.inject.Inject

class DetailsFragment : Fragment() {

    @Inject
    lateinit var viewModel: DetailsViewModel
    private val navigationArgs: DetailsFragmentArgs by navArgs()
    private lateinit var binding: DetailsFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BooksApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BooksDetailsAdapter(binding)
        binding.youLikeRecycler.adapter = adapter
        val bigAdapter = BigBooksAdapter(binding)
        val bigBooksRecycler = binding.bigBookRecycler
        bigBooksRecycler.adapter = bigAdapter

        bigBooksRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_SETTLING || newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val position = getCurrentItem(recyclerView)
                    val book = bigAdapter.getItemByPosition(position)
                    binding.bindInfoAboutBook(book)
                }
            }
        })

        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.bigBookRecycler)

        viewModel.firebaseResponse.observe(viewLifecycleOwner) { firebaseResponse ->
            if (firebaseResponse != null) {
                bigAdapter.submitList(firebaseResponse.books)

                val startPosition = navigationArgs.bookId
                (bigBooksRecycler.layoutManager as LinearLayoutManager).scrollToPosition(
                    startPosition
                )
                binding.bindInfoAboutBook(firebaseResponse.books[startPosition])

                adapter.submitList(firebaseResponse.books.filter {
                    firebaseResponse.likeIds.contains(
                        it.id
                    )
                })
            }
        }
    }

    private fun getCurrentItem(recyclerView: RecyclerView): Int {
        return (recyclerView.layoutManager as LinearLayoutManager)
            .findFirstVisibleItemPosition()
    }
}

fun DetailsFragmentBinding.bindInfoAboutBook(book: Book) {
    readersCount.text = book.views.lowercase()
    likesCount.text = book.likes.lowercase()
    quotesCount.text = book.quotes.lowercase()
    genreValue.text = book.genre
    summary.text = book.summary
}