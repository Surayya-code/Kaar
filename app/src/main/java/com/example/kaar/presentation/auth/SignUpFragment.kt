package com.example.kaar.presentation.auth

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kaar.R
import com.example.kaar.common.BaseFragment
import com.example.kaar.common.utils.RegisterFieldsState
import com.example.kaar.common.utils.RegisterValidation
import com.example.kaar.common.utils.User
import com.example.kaar.databinding.FragmentSignUpBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {
    private val viewModel: SignUpViewModel by viewModels<SignUpViewModel>()

    override fun onViewCreateFinish() {
     lifecycleScope.launch {
         viewModel.validation.collect { validation ->
             if (validation.username is RegisterValidation.Failed) {
                 withContext(Dispatchers.Main) {
                     binding.editTextUsername.apply {
                         requestFocus()
                         error = validation.username.message
                     }
                 }
             } else if (validation.password is RegisterValidation.Failed) {
                 kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
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
                val username = User(
                    editTextUsername.text.toString().trim()
                )
                val password = editTextPassword.text.toString().trim()
                viewModel.createAccountWithUsernameAndPassword(username,password)
                viewModel.saveUsername(username.toString())
                viewModel.savePassword(password.toString())
            }
            textViewLogin.setOnClickListener {
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
            }
        }

        var isChecked = false
         binding.apply {
             buttonChecked.setOnClickListener {
                 val isCheckable = if(isChecked){
                     R.drawable.checkbox_outline
                 }
                 else{
                     R.drawable.checkbox
                 }
             buttonChecked.setImageResource(isCheckable)
               isChecked = !isChecked
             }
         }
    }
    override fun observeEvents() {

    }

}