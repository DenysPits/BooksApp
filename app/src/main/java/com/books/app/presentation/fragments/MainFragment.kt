package com.books.app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.books.app.R
import com.books.app.databinding.MainFragmentBinding
import com.books.app.presentation.adapters.Book
import com.books.app.presentation.adapters.Genre
import com.books.app.presentation.adapters.GenreAdapter
import com.books.app.presentation.adapters.ViewPagerAdapter
import com.books.app.presentation.viewmodels.MainViewModel

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val images = intArrayOf(
            R.drawable.banner,
            R.drawable.banner,
            R.drawable.banner,
            R.drawable.banner,
            R.drawable.banner
        )
        val viewPager = binding.banner
        val viewPagerAdapter = ViewPagerAdapter(requireContext(), images)
        val tabLayout = binding.tabLayout
        viewPager.pageMargin = 80
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        val genres = listOf(
            Genre(
                "Fantasy",
                listOf(
                    Book(
                        "The Sandman",
                        "https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/the_sandman.jpg?alt=media&token=db0c3806-da65-4d96-ba4c-f6cf27a92cd3"
                    ),
                    Book(
                        "American Gods",
                        "https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/american_gods.jpg?alt=media&token=faabd9a5-8f3a-4253-b6c6-8da845d28b25"
                    ),
                    Book(
                        "Dune",
                        "https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/dune.jpg?alt=media&token=54dc04b9-665b-41de-9cf2-b3062f649296"
                    ),
                    Book(
                        "Leviathan Wakes",
                        "https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/leviathan_wakes.jpg?alt=media&token=cec55caf-0eb2-4c14-8d69-fce35b49a164"
                    )
                )
            ), Genre(
                "Science",
                listOf(
                    Book(
                        "The Sandman",
                        "https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/the_sandman.jpg?alt=media&token=db0c3806-da65-4d96-ba4c-f6cf27a92cd3"
                    ),
                    Book(
                        "American Gods",
                        "https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/american_gods.jpg?alt=media&token=faabd9a5-8f3a-4253-b6c6-8da845d28b25"
                    ),
                    Book(
                        "Dune",
                        "https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/dune.jpg?alt=media&token=54dc04b9-665b-41de-9cf2-b3062f649296"
                    ),
                    Book(
                        "Leviathan Wakes",
                        "https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/leviathan_wakes.jpg?alt=media&token=cec55caf-0eb2-4c14-8d69-fce35b49a164"
                    )
                )
            ), Genre(
                "Romance",
                listOf(
                    Book(
                        "The Sandman",
                        "https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/the_sandman.jpg?alt=media&token=db0c3806-da65-4d96-ba4c-f6cf27a92cd3"
                    ),
                    Book(
                        "American Gods",
                        "https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/american_gods.jpg?alt=media&token=faabd9a5-8f3a-4253-b6c6-8da845d28b25"
                    ),
                    Book(
                        "Dune",
                        "https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/dune.jpg?alt=media&token=54dc04b9-665b-41de-9cf2-b3062f649296"
                    ),
                    Book(
                        "Leviathan Wakes",
                        "https://firebasestorage.googleapis.com/v0/b/bookapp-b1f1b.appspot.com/o/leviathan_wakes.jpg?alt=media&token=cec55caf-0eb2-4c14-8d69-fce35b49a164"
                    )
                )
            )
        )
        val genreAdapter = GenreAdapter()
        binding.genreRecycler.isNestedScrollingEnabled = false
        binding.genreRecycler.adapter = genreAdapter
        genreAdapter.submitList(genres)
    }
}