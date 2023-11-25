package com.example.kaar.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kaar.common.BaseFragment
import com.example.kaar.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint


/*class SplashFragment : BaseFragment<FragmentSplashBinding> (FragmentSplashBinding::inflate) {
    private val viewModel : SplashViewModel by viewModels()

    override fun onViewCreateFinish() {
        binding.apply {
            lottiLoading.alpha = 1f
            lottiLoading.animate().setDuration(5500).alpha(1f).start()

        }
        finishActivity()
    }
    override fun observeEvents() {
        viewModel.auth.observe(viewLifecycleOwner){
            if (it){
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment())
            }else{
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }
    }


    fun finishActivity(){
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
}*/
@AndroidEntryPoint
class SplashFragment : Fragment(){
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val viewModel : SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lottiLoading.alpha = 1f
            lottiLoading.animate().setDuration(5500).alpha(1f).start()

        }
        finishActivity()
        observeEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun finishActivity(){
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
     fun observeEvents() {
        viewModel.auth.observe(viewLifecycleOwner){
            if (it){
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment())
            }else{
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }
    }
}
