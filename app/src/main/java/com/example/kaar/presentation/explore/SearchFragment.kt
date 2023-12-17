package com.example.kaar.presentation.explore


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.kaar.common.base.BaseFragment
import com.example.kaar.databinding.FragmentSearchBinding
import com.example.kaar.presentation.explore.adapter.viewPagerTabLayoutAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var adapter: viewPagerTabLayoutAdapter
   private val args by navArgs<SearchFragmentArgs>()

    override fun onViewCreateFinish() {
        setViewPager()
       // getData(args.id.toInt())
    }

    override fun observeEvents() {

    }
//    private fun getData(id: Int) {
//      viewModel.getNewsDetail(id)
//    }
    private fun setViewPager(){
        with(binding) {
            viewPagerTablayout.adapter =
                viewPagerTabLayoutAdapter(childFragmentManager, lifecycle,1)

            val tabsArray = arrayOf(
                "News",
                "Topics",
                "Author",
            )

            TabLayoutMediator(tabLayout, viewPagerTablayout) { tab, position ->
                tab.text = tabsArray[position]
            }.attach()
        }
    }

}