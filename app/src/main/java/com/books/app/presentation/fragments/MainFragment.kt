package com.books.app.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asksira.loopingviewpager.LoopingViewPager
import com.books.app.R
import com.books.app.databinding.MainFragmentBinding
import com.books.app.presentation.BooksApplication
import com.books.app.presentation.adapters.BannerAdapter
import com.books.app.presentation.adapters.GenreAdapter
import com.books.app.presentation.decorations.MyPageIndicator
import com.books.app.presentation.viewmodels.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment() {
    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var viewPager: LoopingViewPager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BooksApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.progress.observe(viewLifecycleOwner) {
            val progressValue = it ?: 0
            if (progressValue == 100) {
                binding.splashScreen.root.visibility = View.GONE
            } else {
                binding.splashScreen.progressHorizontal.progress = progressValue
            }
        }

        val genreAdapter = GenreAdapter()
        binding.genreRecycler.isNestedScrollingEnabled = false
        binding.genreRecycler.adapter = genreAdapter

        viewPager = binding.banner
        viewPager.pageMargin = 80

        viewModel.firebaseResponseLiveData.observe(viewLifecycleOwner) { firebaseResponse ->
            if (firebaseResponse != null) {
                genreAdapter.submitList(viewModel.genres)

                val banners = firebaseResponse.banners
                val viewPagerAdapter = BannerAdapter(banners)
                viewPager.adapter = viewPagerAdapter

                val indicator = MyPageIndicator(
                    requireContext(),
                    binding.linear,
                    viewPager,
                    R.drawable.tab_selector
                )
                indicator.setSize(R.dimen.banner_indicator_size)
                indicator.setInitialPage(0)
                indicator.setSpacingRes(R.dimen.banner_space_between_dots)
                indicator.setPageCount(banners.size)
                indicator.show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewPager.resumeAutoScroll()
    }

    override fun onPause() {
        viewPager.pauseAutoScroll()
        super.onPause()
    }
}