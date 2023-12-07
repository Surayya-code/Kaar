package com.example.kaar.presentation.auth

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kaar.R
import com.example.kaar.common.base.BaseFragment
import com.example.kaar.common.utils.RegisterValidation
import com.example.kaar.common.utils.Resource
import com.example.kaar.common.utils.User
import com.example.kaar.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {
    private val viewModel: SignInViewModel by viewModels<SignInViewModel>()
    override fun onViewCreateFinish() {
        binding.textViewForgotPassword.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }

        lifecycleScope.launch {
            viewModel.validation.collect { validation ->
                if (validation.email is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {
                        binding.editTextEmail.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                } else if (validation.password is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {
                        binding.editTextPassword.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }
            }
        }


        binding.apply {
            btnCircularProgress.setOnClickListener {
                val user = User(
                    editTextEmail.text.toString().trim()
                )
                val password = editTextPassword.text.toString().trim()
                viewModel.loginUser(user, password)
                viewModel.saveEmail(user.toString())
                viewModel.savePassword(password)

            }
            textViewForgotPassword.setOnClickListener {
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToForgetPasswordFragment())
            }
        }
        var isChecked = false
        binding.apply {
            imageButtonChecked.setOnClickListener {
                val checkable = if (isChecked) {
                    R.drawable.checkbox_outline
                } else {
                    R.drawable.checkbox
                }
                imageButtonChecked.setImageResource(checkable)
                isChecked = !isChecked
            }

        }
        onBackPress()
    }

    private fun onBackPress(){
        requireActivity().onBackPressedDispatcher.addCallback(this,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(SignInFragmentDirections.toSplashFragment())
            }
        })
    }

    override fun observeEvents() {
        with(viewModel){
            authResult.observe(viewLifecycleOwner){
                when(it){
                    is Resource.Loading->{
                        binding.btnCircularProgress.startAnimation()
                    }
                    is Resource.Success->{
                        binding.btnCircularProgress.revertAnimation()
                        Toast.makeText(context, "Welcome to Kaars!",Toast.LENGTH_LONG).show()
                        findNavController().navigate(SignInFragmentDirections.toHomeFragment())
                    }
                    is Resource.Error->{
                        binding.btnCircularProgress.revertAnimation()
                        Toast.makeText(context,"Ohh,sorry, your email address is not registered :(",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            resetPassword.observe(viewLifecycleOwner){
                when(it){
                    is Resource.Loading->{

                    }
                    is Resource.Success->{
                        Snackbar.make(requireView(),
                            "Reset link was sent to your email",
                            Snackbar.LENGTH_LONG
                            ).show()
                    }
                    is Resource.Error->{
                        Snackbar.make(requireView(),
                            "Error ${it.exception}",
                            Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }



}