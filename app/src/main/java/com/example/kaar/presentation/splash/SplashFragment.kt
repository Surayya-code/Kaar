package com.example.kaar.presentation.splash


import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kaar.common.base.BaseFragment
import com.example.kaar.common.NetworkConnection
import com.example.kaar.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel : SplashViewModel by viewModels()
    @Inject
    lateinit var networkConnection: NetworkConnection

    override fun onViewCreateFinish() {

        networkConnection = NetworkConnection(requireContext())
        observeNetworkConnection()

        binding.apply {
            lottiLoading.alpha = 1f
            lottiLoading.animate().setDuration(5500).alpha(1f).start()
        }
        finishActivity()
    }


    override fun observeEvents() {}
    private fun observeNetworkConnection() {
        networkConnection.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                Toast.makeText(requireContext(), "Connected", Toast.LENGTH_SHORT).show()
                viewModel.auth.observe(viewLifecycleOwner){
                    if (it){
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment())
                    }else{
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
                    }
                }
            } else {
                Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun finishActivity(){
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
}

