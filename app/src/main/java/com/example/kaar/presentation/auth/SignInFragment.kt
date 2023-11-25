package com.example.kaar.presentation.auth

import androidx.navigation.fragment.findNavController
import com.example.kaar.common.BaseFragment
import com.example.kaar.databinding.FragmentSignInBinding


class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {
    override fun observeEvents() {

    }

    override fun onViewCreateFinish() {
        binding.textViewForgotPassword.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToForgetPasswordFragment())
        }
    }

}