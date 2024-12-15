package com.avicenna.enterprise.solutions.news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.avicenna.enterprise.solutions.news.MyApplication
import com.avicenna.enterprise.solutions.news.R
import com.avicenna.enterprise.solutions.news.databinding.FragmentFavoriteBinding
import com.avicenna.enterprise.solutions.news.ui.adapters.FavoriteAdapter
import com.avicenna.enterprise.solutions.news.ui.viewmodels.MainViewModel
import com.avicenna.enterprise.solutions.news.ui.viewmodels.MainViewModelFactory

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
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
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
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
        shareViewModel.favorites.observe(viewLifecycleOwner) {
            val adapter =  FavoriteAdapter()
            adapter.submitList(it)
            binding.rvFavorites.adapter = adapter

            /**
             * by clicking item
             * save in selected
             * go to content fragment
             */
            adapter.setOnItemClickListener(object : FavoriteAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val current = adapter.currentList[position]
                    shareViewModel.select(current)
                    goToContentFragment()
                }
            })
        }
    }

    private fun goToContentFragment() {
        findNavController().navigate(R.id.action_favoriteFragment2_to_contentFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}