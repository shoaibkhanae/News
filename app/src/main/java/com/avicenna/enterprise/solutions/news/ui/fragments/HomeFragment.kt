package com.avicenna.enterprise.solutions.news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.avicenna.enterprise.solutions.news.R
import com.avicenna.enterprise.solutions.news.data.models.Article
import com.avicenna.enterprise.solutions.news.databinding.FragmentHomeBinding
import com.avicenna.enterprise.solutions.news.ui.adapters.NewsAdapter
import com.avicenna.enterprise.solutions.news.ui.viewmodels.MainViewModel
import com.avicenna.enterprise.solutions.news.utils.Response
import com.google.android.material.chip.Chip

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    val binding
        get() = _binding!!

    private val shareViewModel: MainViewModel by activityViewModels()


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
        binding.tvSeeAll.setOnClickListener {
            goToSearchFragment()
        }
        binding.ivArrow.setOnClickListener {
            goToSearchFragment()
        }
    }

    private fun showNewsWithCategory() {
        binding.cgCategory.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                val selectedChip = group.findViewById<Chip>(checkedId)
                shareViewModel.getNewsWithCategory(selectedChip.text.toString())
            }
        }
    }

    private fun setupLatestNewsUI() {
        shareViewModel.news.observe(viewLifecycleOwner) {
            when(it) {
                is Response.Success -> {
                    binding.pbLatest.visibility = View.GONE
                    setupLatestAdapter(it.data!!.articles)
                }
                is Response.Error -> {
                    binding.pbLatest.visibility = View.GONE
                    showToast(it.error!!)
                }
                is Response.Loading -> {
                    binding.pbLatest.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun setupLatestAdapter(articles: List<Article>, type: Int = 1) {
        val adapter = NewsAdapter(articles, type)
        binding.rvLatestNews.adapter = adapter

        adapter.articleSelectedListener = object : NewsAdapter.ArticleSelectedListener {
            override fun articleSelected(article: Article) {
                shareViewModel.select(article)
                goToContentFragment()
            }
        }
    }

    private fun setupCategoryNewsUI() {
        shareViewModel.category.observe(viewLifecycleOwner) {
            when(it) {
                is Response.Success -> {
                    binding.pbCategory.visibility = View.GONE
                    setupCategoryAdapter(it.data!!.articles)
                }
                is Response.Error -> {
                    binding.pbCategory.visibility = View.GONE
                    showToast(it.error!!)
                }
                is Response.Loading -> {
                    binding.pbCategory.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupCategoryAdapter(articles: List<Article>, type: Int = 2) {
        val adapter = NewsAdapter(articles, type)
        binding.rvCategories.adapter = adapter

        adapter.articleSelectedListener = object : NewsAdapter.ArticleSelectedListener {
            override fun articleSelected(article: Article) {
                shareViewModel.select(article)
                goToContentFragment()
            }
        }
    }

    private fun goToContentFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_contentFragment)
    }

    private fun goToSearchFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}