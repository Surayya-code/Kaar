package com.example.kaar.presentation.onBoarding

import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.kaar.common.base.BaseFragment
import com.example.kaar.databinding.FragmentOnBoardingBinding


class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>(FragmentOnBoardingBinding::inflate) {

        private lateinit var viewPager2: ViewPager2

        private val pager2CallBack = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            if (position== PageViewLists.pageSlides.size -1){
                binding.btnNext.text="Get Started"
                binding.btnNext.setOnClickListener {
                    findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToSignUpFragment())
                }
            }else{
                binding.btnNext.text="Next"
                binding.btnNext.setOnClickListener {
                    viewPager2.currentItem = position+1
                }
            }
        }
    }
        override fun onViewCreateFinish() {
            viewPager(binding)
            onBackPress()
    }
    private fun viewPager(binding: FragmentOnBoardingBinding){

       val adapter = PagerAdapter(PageViewLists.pageSlides)
       viewPager2=binding.viewPager
       viewPager2.adapter=adapter
       viewPager2.registerOnPageChangeCallback(pager2CallBack)
       binding.dotsIndicator.setViewPager2(viewPager2)
    }

    fun onBackPress(){
        requireActivity().onBackPressedDispatcher
            .addCallback(this,object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    findNavController().navigate(OnBoardingFragmentDirections.toSplashScreen())
                }
            })
    }

    override fun observeEvents() {

    }


}