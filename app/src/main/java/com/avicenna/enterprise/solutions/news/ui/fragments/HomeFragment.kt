package com.avicenna.enterprise.solutions.news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.avicenna.enterprise.solutions.news.MyApplication
import com.avicenna.enterprise.solutions.news.databinding.FragmentHomeBinding
import com.avicenna.enterprise.solutions.news.ui.adapters.NewsAdapter
import com.avicenna.enterprise.solutions.news.ui.viewmodels.NewsViewModel
import com.avicenna.enterprise.solutions.news.ui.viewmodels.NewsViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    val binding
        get() = _binding!!

    private val viewModel: NewsViewModel by viewModels {
        NewsViewModelFactory((requireActivity().application as MyApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        showNewsWithCategory()
        setupLatestNewsUI()
        setupCategoryNewsUI()
    }

    private fun showNewsWithCategory() {
        binding.chHealth.setOnClickListener {
            viewModel.getNewsWithCategory("health")
        }
        binding.chSports.setOnClickListener {
            viewModel.getNewsWithCategory("sports")
        }
        binding.chGeneral.setOnClickListener {
            viewModel.getNewsWithCategory("general")
        }
        binding.chScience.setOnClickListener {
            viewModel.getNewsWithCategory("science")
        }
        binding.chBusiness.setOnClickListener {
            viewModel.getNewsWithCategory("business")
        }
        binding.chEntertainment.setOnClickListener {
            viewModel.getNewsWithCategory("entertainment")
        }
        binding.chTechnology.setOnClickListener {
            viewModel.getNewsWithCategory("technology")
        }
    }

    private fun setupLatestNewsUI() {
        viewModel.news.observe(viewLifecycleOwner) {
            val adapter = NewsAdapter(it.articles, 1)
            binding.rvLatestNews.adapter = adapter
        }
    }

    private fun setupCategoryNewsUI() {
        viewModel.category.observe(viewLifecycleOwner) {
            val adapter = NewsAdapter(it.articles, 2)
            binding.rvCategories.adapter = adapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}