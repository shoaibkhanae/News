package com.avicenna.enterprise.solutions.news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.transition.TransitionInflater
import com.avicenna.enterprise.solutions.news.MyApplication
import com.avicenna.enterprise.solutions.news.databinding.FragmentContentBinding
import com.avicenna.enterprise.solutions.news.ui.viewmodels.HomeViewModel
import com.avicenna.enterprise.solutions.news.ui.viewmodels.HomeViewModelFactory


class ContentFragment : Fragment() {
    private var _binding: FragmentContentBinding? = null
    val binding
        get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory((requireActivity().application as MyApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        showArticleContent()
    }

    private fun showArticleContent() {
        homeViewModel.selected.observe(viewLifecycleOwner) { article ->

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}