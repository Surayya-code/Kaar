package com.example.kaar.presentation.home

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.kaar.common.base.BaseFragment
import com.example.kaar.common.utils.gone
import com.example.kaar.common.utils.visible
import com.example.kaar.databinding.FragmentHomeBinding
import com.example.kaar.domain.ArticleUiState
import com.example.kaar.presentation.home.adapter.NewsHomeAdapter
import com.example.kaar.presentation.home.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel:HomeViewModel by viewModels()
    private val newsHomeAdapter= NewsHomeAdapter()
    private val trendingPageAdapter = ViewPagerAdapter()
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var viewPager2: ViewPager2
    override fun onViewCreateFinish() {
        viewPager2=binding.viewPager
        setRecyclerView()
        startAutoPageSwitch()
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

    }


    override fun observeEvents() {

        with(viewModel){
            newsState.observe(viewLifecycleOwner) { articleUIState ->
                when (articleUIState) {
                    is ArticleUiState.Success -> {
                        binding.progressBar4.gone()
                        newsHomeAdapter.differ.submitList(articleUIState.data)

                    }
                    is ArticleUiState.Error -> {
                        binding.progressBar4.gone()
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                    is ArticleUiState.Loading -> {
                        binding.progressBar4.visible()
                    }
                    else -> {}
                }
            }

            trendNews.observe(viewLifecycleOwner){articleUIState ->
                when (articleUIState) {
                    is ArticleUiState.Success -> {
                       // binding.progressBar4.gone()
                        trendingPageAdapter.differ.submitList(articleUIState.data)

                    }
                    is ArticleUiState.Error -> {
                       // binding.progressBar4.gone()
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                    is ArticleUiState.Loading -> {
                        binding.progressBar4.visible()
                    }
                    else -> {}
                }
            }
        }

    }

    private fun setRecyclerView() {
        with(binding) {
            rvDailyNews.adapter = newsHomeAdapter
            viewPager.adapter = trendingPageAdapter

        }
    }
    private fun startAutoPageSwitch() {
        val runnable = object : Runnable {
            override fun run() {
                val currentItem = viewPager2.currentItem
                val nextItem =
                    if (currentItem == viewPager2.adapter?.itemCount?.minus(1)) 0 else currentItem + 1
                viewPager2.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 3000)
            }
        }
        handler.postDelayed(runnable, 3000)
    }
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

}