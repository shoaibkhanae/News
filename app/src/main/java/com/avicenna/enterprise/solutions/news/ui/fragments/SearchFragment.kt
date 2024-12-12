package com.avicenna.enterprise.solutions.news.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.avicenna.enterprise.solutions.news.MyApplication
import com.avicenna.enterprise.solutions.news.databinding.FragmentSearchBinding
import com.avicenna.enterprise.solutions.news.ui.adapters.NewsAdapter
import com.avicenna.enterprise.solutions.news.ui.viewmodels.SearchNewsViewModel
import com.avicenna.enterprise.solutions.news.ui.viewmodels.SearchNewsViweModelFactory


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    val binding
        get() = _binding!!

    private val searchViewModel: SearchNewsViewModel by viewModels {
        SearchNewsViweModelFactory((requireActivity().application as MyApplication).repository)
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
            val adapter = NewsAdapter(it.articles, 1)
            binding.rvSearch.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}