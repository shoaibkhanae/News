package com.avicenna.enterprise.solutions.news.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.avicenna.enterprise.solutions.news.MyApplication
import com.avicenna.enterprise.solutions.news.R
import com.avicenna.enterprise.solutions.news.data.model.Article
import com.avicenna.enterprise.solutions.news.databinding.FragmentSearchBinding
import com.avicenna.enterprise.solutions.news.ui.adapters.NewsAdapter
import com.avicenna.enterprise.solutions.news.ui.viewmodels.MainViewModel
import com.avicenna.enterprise.solutions.news.ui.viewmodels.MainViewModelFactory
import com.avicenna.enterprise.solutions.news.utils.Response
import com.google.android.material.chip.Chip


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    val binding
        get() = _binding!!

    private val shareViewModel: MainViewModel by activityViewModels {
        MainViewModelFactory((requireActivity().application as MyApplication).repository)
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
                    shareViewModel.searchNews(text.toString())
                }
            }
        })
    }

    private fun setupUI() {
        shareViewModel.searched.observe(viewLifecycleOwner) {
            when(it) {
                is Response.Success -> {
                    hideProgressbar()
                    setupAdapter(it.data!!.articles)
                }
                is Response.Error -> {
                    hideProgressbar()
                }
                is Response.Loading -> {
                    showProgressbar()
                }
            }
        }
    }

    private fun setupAdapter(articles: List<Article>, type: Int = 1) {
        val adapter = NewsAdapter(articles, type)
        binding.rvSearch.adapter = adapter

        adapter.articleSelectedListener = object : NewsAdapter.ArticleSelectedListener {
            override fun articleSelected(article: Article) {
                shareViewModel.select(article)
                goToContentFragment()
            }
        }
    }

    private fun goToContentFragment() {
        findNavController().navigate(R.id.action_searchFragment_to_contentFragment)
    }

    private fun showProgressbar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressbar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showNewsWithCategory() {
        binding.cgCategory.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                val selectedChip = group.findViewById<Chip>(checkedId)
                var chipText = selectedChip?.text.toString()
                if (chipText == "Filter") {
                    chipText = "general"
                }
                shareViewModel.searchNewsWithCategory(chipText)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}