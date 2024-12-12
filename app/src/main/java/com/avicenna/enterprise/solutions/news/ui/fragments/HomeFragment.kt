package com.avicenna.enterprise.solutions.news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.avicenna.enterprise.solutions.news.MyApplication
import com.avicenna.enterprise.solutions.news.R
import com.avicenna.enterprise.solutions.news.databinding.FragmentHomeBinding
import com.avicenna.enterprise.solutions.news.ui.adapters.NewsAdapter
import com.avicenna.enterprise.solutions.news.ui.viewmodels.HomeViewModel
import com.avicenna.enterprise.solutions.news.ui.viewmodels.HomeViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    val binding
        get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory((requireActivity().application as MyApplication).repository)
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
        binding.cdSearch.setOnClickListener {
            goToSearchFragment()
        }
    }

    private fun showNewsWithCategory() {
        binding.chHealth.setOnClickListener {
            homeViewModel.getNewsWithCategory("health")
        }
        binding.chSports.setOnClickListener {
            homeViewModel.getNewsWithCategory("sports")
        }
        binding.chGeneral.setOnClickListener {
            homeViewModel.getNewsWithCategory("general")
        }
        binding.chScience.setOnClickListener {
            homeViewModel.getNewsWithCategory("science")
        }
        binding.chBusiness.setOnClickListener {
            homeViewModel.getNewsWithCategory("business")
        }
        binding.chEntertainment.setOnClickListener {
            homeViewModel.getNewsWithCategory("entertainment")
        }
        binding.chTechnology.setOnClickListener {
            homeViewModel.getNewsWithCategory("technology")
        }
    }

    private fun setupLatestNewsUI() {
        homeViewModel.news.observe(viewLifecycleOwner) {
            val adapter = NewsAdapter(it.articles, 1)
            binding.rvLatestNews.adapter = adapter
        }
    }

    private fun setupCategoryNewsUI() {
        homeViewModel.category.observe(viewLifecycleOwner) {
            val adapter = NewsAdapter(it.articles, 2)
            binding.rvCategories.adapter = adapter
        }
    }

    private fun goToSearchFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}