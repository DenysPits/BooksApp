package com.books.app.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.books.app.databinding.SplashFragmentBinding
import com.books.app.presentation.BooksApplication
import com.books.app.presentation.viewmodels.SplashViewModel
import javax.inject.Inject

class SplashFragment : Fragment() {

    @Inject
    lateinit var viewModel: SplashViewModel
    lateinit var binding: SplashFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BooksApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.progress.observe(viewLifecycleOwner) {
            val progressValue = it ?: 0
            if (progressValue == 100) {
                val action = SplashFragmentDirections.actionSplashFragmentToMainFragment()
                findNavController().navigate(action)
            } else {
                binding.progressHorizontal.progress = progressValue
            }
        }
    }
}