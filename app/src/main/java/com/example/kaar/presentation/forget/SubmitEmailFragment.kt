package com.example.kaar.presentation.forget


import androidx.navigation.fragment.findNavController
import com.example.kaar.common.base.BaseFragment
import com.example.kaar.databinding.FragmentSubmitEmailBinding


class SubmitEmailFragment : BaseFragment<FragmentSubmitEmailBinding>(FragmentSubmitEmailBinding::inflate) {
    override fun observeEvents() {

    }

    override fun onViewCreateFinish() {
        binding.submitBtn.setOnClickListener {
            findNavController().navigate(SubmitEmailFragmentDirections.actionSubmitEmailFragmentToOTPVerificationFragment())
        }
    }

}