package com.avicenna.enterprise.solutions.news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.avicenna.enterprise.solutions.news.databinding.FragmentHomeBinding
import com.avicenna.enterprise.solutions.news.ui.adapters.LatestNewsAdapter
import com.avicenna.enterprise.solutions.news.ui.viewmodels.NewsViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    val binding
        get() = _binding!!

    private val viemodel: NewsViewModel by viewModels()

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
        setupUI()
    }

    private fun setupUI() {
        viemodel.news.observe(viewLifecycleOwner) {
            val adapter = LatestNewsAdapter(it.articles)
            binding.rvLatestNews.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}