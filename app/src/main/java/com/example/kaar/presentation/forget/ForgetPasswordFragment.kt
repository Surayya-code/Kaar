package com.example.kaar.presentation.forget


import androidx.navigation.fragment.findNavController
import com.example.kaar.common.base.BaseFragment
import com.example.kaar.databinding.FragmentForgetPasswordBinding


class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding>(FragmentForgetPasswordBinding::inflate) {
    override fun observeEvents() {

    }

    override fun onViewCreateFinish() {
        binding.submitBtn.setOnClickListener {
            findNavController().navigate(ForgetPasswordFragmentDirections.actionForgetPasswordFragmentToSubmitEmailFragment())
        }
    }

}