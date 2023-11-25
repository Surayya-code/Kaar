package com.example.kaar.presentation.forget


import androidx.navigation.fragment.findNavController
import com.example.kaar.common.BaseFragment
import com.example.kaar.databinding.FragmentForgetPasswordBinding
import com.example.kaar.presentation.auth.SignUpFragmentDirections


class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding>(FragmentForgetPasswordBinding::inflate) {
    override fun observeEvents() {

    }

    override fun onViewCreateFinish() {
        binding.submitBtn.setOnClickListener {
            findNavController().navigate(ForgetPasswordFragmentDirections.actionForgetPasswordFragmentToSubmitEmailFragment())
        }
    }

}