package com.example.kaar.presentation.explore.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kaar.presentation.explore.AuthorFragment
import com.example.kaar.presentation.explore.NewsFragment
import com.example.kaar.presentation.explore.TopicsFragment

class viewPagerTabLayoutAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val id: Int
)
    : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                NewsFragment()
            }
            1->{
                TopicsFragment()
            }
            2->{
                AuthorFragment()
            }
            else-> Fragment()
        }
    }

}