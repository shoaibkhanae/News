package com.avicenna.enterprise.solutions.news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.transition.TransitionInflater
import coil.load
import com.avicenna.enterprise.solutions.news.MyApplication
import com.avicenna.enterprise.solutions.news.R
import com.avicenna.enterprise.solutions.news.data.local.Article
import com.avicenna.enterprise.solutions.news.databinding.FragmentContentBinding
import com.avicenna.enterprise.solutions.news.ui.viewmodels.MainViewModel
import com.avicenna.enterprise.solutions.news.ui.viewmodels.MainViewModelFactory


class ContentFragment : Fragment() {
    private var _binding: FragmentContentBinding? = null
    val binding
        get() = _binding!!

    private val shareViewModel: MainViewModel by activityViewModels {
        MainViewModelFactory((requireActivity().application as MyApplication).repository)
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
        binding.btnFavorite.setOnClickListener { addToFavorite() }
    }

    private fun showArticleContent() {
        shareViewModel.selected.observe(viewLifecycleOwner) { article ->
            binding.apply {
                ivNews.load(article.urlToImage) {
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.news_placeholder)
                }
                tvTitle.text = article.title
                tvAuthor.text = article.author
                tvContent.text = article.content
            }
        }
    }

    private fun addToFavorite() {
        shareViewModel.selected.observe(viewLifecycleOwner) {
            val article = Article(imageUrl = it.urlToImage, title = it.title)
            shareViewModel.insert(article)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}