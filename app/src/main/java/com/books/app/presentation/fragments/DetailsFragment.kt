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
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.books.app.R
import com.books.app.databinding.DetailsFragmentBinding
import com.books.app.presentation.BooksApplication
import com.books.app.presentation.adapters.BigBooksAdapter
import com.books.app.presentation.adapters.BooksDetailsAdapter
import com.books.app.presentation.adapters.HorizontalMarginItemDecoration
import com.books.app.presentation.viewmodels.DetailsViewModel
import com.books.domain.entities.Book
import javax.inject.Inject
import kotlin.math.abs


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

        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val bigAdapter = BigBooksAdapter()
        val viewPager = binding.bigBookPager
        viewPager.adapter = bigAdapter
        viewPager.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.2f * abs(position))
            page.scaleX = 1 - (0.2f * abs(position))
        }
        viewPager.setPageTransformer(pageTransformer)

        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        viewPager.addItemDecoration(itemDecoration)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val book = bigAdapter.getItemByPosition(position)
                binding.bindInfoAboutBook(book)
            }
        })

        viewModel.firebaseResponse.observe(viewLifecycleOwner) { firebaseResponse ->
            if (firebaseResponse != null) {
                val startPosition = navigationArgs.bookId

                bigAdapter.submitList(firebaseResponse.books) {
                    viewPager.currentItem = startPosition
                    binding.bindInfoAboutBook(firebaseResponse.books[startPosition])
                }

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
            .findFirstCompletelyVisibleItemPosition()
    }
}

fun DetailsFragmentBinding.bindInfoAboutBook(book: Book) {
    title.text = book.title
    author.text = book.author
    readersCount.text = book.views.lowercase()
    likesCount.text = book.likes.lowercase()
    quotesCount.text = book.quotes.lowercase()
    genreValue.text = book.genre
    summary.text = book.summary
}