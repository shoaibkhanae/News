package com.avicenna.enterprise.solutions.news.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.avicenna.enterprise.solutions.news.MyApplication
import com.avicenna.enterprise.solutions.news.R
import com.avicenna.enterprise.solutions.news.data.model.Article
import com.avicenna.enterprise.solutions.news.databinding.FragmentSearchBinding
import com.avicenna.enterprise.solutions.news.ui.adapters.NewsAdapter
import com.avicenna.enterprise.solutions.news.ui.viewmodels.HomeViewModel
import com.avicenna.enterprise.solutions.news.ui.viewmodels.HomeViewModelFactory
import com.avicenna.enterprise.solutions.news.ui.viewmodels.SearchNewsViewModel
import com.avicenna.enterprise.solutions.news.ui.viewmodels.SearchNewsViweModelFactory
import com.avicenna.enterprise.solutions.news.utils.Response


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    val binding
        get() = _binding!!

    private val searchViewModel: SearchNewsViewModel by viewModels {
        SearchNewsViweModelFactory((requireActivity().application as MyApplication).repository)
    }

    private val homeViewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory((requireActivity().application as MyApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setupSearchFunctionality()
        setupUI()
        showNewsWithCategory()
    }

    private fun setupSearchFunctionality() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(text: Editable?) {
                if (text != null) {
                    searchViewModel.searchNews(text.toString())
                }
            }
        })
    }

    private fun setupUI() {
        searchViewModel.searched.observe(viewLifecycleOwner) {
            when(it) {
                is Response.Success -> {
                    binding.progressBar.visibility = View.GONE
                    setupAdapter(it.data!!.articles)
                }
                is Response.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
                is Response.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupAdapter(articles: List<Article>, type: Int = 1) {
        val adapter = NewsAdapter(articles, type)
        binding.rvSearch.adapter = adapter

        adapter.articleSelectedListener = object : NewsAdapter.ArticleSelectedListener {
            override fun articleSelected(article: Article) {
                homeViewModel.select(article)
                binding.progressBar.visibility = View.GONEf
                goToContentFragment()
            }
        }
    }

    private fun goToContentFragment() {
        findNavController().navigate(R.id.action_searchFragment_to_contentFragment)
    }

    private fun showNewsWithCategory() {
        binding.chHealth.setOnClickListener {
            searchViewModel.searchNewsWithCategory("health")
        }
        binding.chSports.setOnClickListener {
            searchViewModel.searchNewsWithCategory("sports")
        }
        binding.chGeneral.setOnClickListener {
            searchViewModel.searchNewsWithCategory("general")
        }
        binding.chScience.setOnClickListener {
            searchViewModel.searchNewsWithCategory("science")
        }
        binding.chBusiness.setOnClickListener {
            searchViewModel.searchNewsWithCategory("business")
        }
        binding.chEntertainment.setOnClickListener {
            searchViewModel.searchNewsWithCategory("entertainment")
        }
        binding.chTechnology.setOnClickListener {
            searchViewModel.searchNewsWithCategory("technology")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}