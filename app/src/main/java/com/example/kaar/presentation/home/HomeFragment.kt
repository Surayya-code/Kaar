package com.example.kaar.presentation.home

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kaar.common.base.BaseFragment
import com.example.kaar.common.utils.gone
import com.example.kaar.common.utils.visible
import com.example.kaar.databinding.FragmentHomeBinding
import com.example.kaar.domain.NewsUiState
import com.example.kaar.presentation.home.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    private val viewModel:HomeViewModel by viewModels()
    private val newsProductAdapter=NewsAdapter()
    override fun onViewCreateFinish() {
        binding.textView17.setOnClickListener{
            findNavController().navigate(
             HomeFragmentDirections.actionHomeFragmentToTrendingFragment()

            )
        }
        binding.rvDailyNews.setOnClickListener{
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToTrendingFragment()
            )
        }
        setRecyclerView()
    }


    override fun observeEvents() {

        with(viewModel){
            getNews.observe(viewLifecycleOwner) { newsUiState ->
                when (newsUiState) {
                    is NewsUiState.Success -> {
                        binding.progressBar4.gone()
                        newsProductAdapter.differ.submitList(newsUiState.data.body())
                    }
                    is NewsUiState.Error -> {
                        binding.progressBar4.gone()
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                    is NewsUiState.Loading -> {
                        binding.progressBar4.visible()
                    }
                    else -> {}
                }
            }
        }

    }

    private fun setRecyclerView() {
        with(binding) {
            rvDailyNews.adapter = newsProductAdapter

        }
    }

}