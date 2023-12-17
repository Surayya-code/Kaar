package com.example.kaar.presentation.explore


import androidx.fragment.app.viewModels
import com.example.kaar.common.base.BaseFragment
import com.example.kaar.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment() : BaseFragment<FragmentNewsBinding>(FragmentNewsBinding::inflate) {
    private val viewModel by viewModels<NewsViewModel>()
    //private val trailerAdapter = NewsAdapter()

    override fun observeEvents() {

    }

    override fun onViewCreateFinish() {

    }


}