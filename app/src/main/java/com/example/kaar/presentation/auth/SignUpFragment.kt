package com.example.kaar.presentation.auth

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kaar.R
import com.example.kaar.common.base.BaseFragment
import com.example.kaar.common.utils.RegisterValidation
import com.example.kaar.common.utils.User
import com.example.kaar.databinding.FragmentSignUpBinding
import com.example.kaar.domain.AuthUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {
    private val viewModel: SignUpViewModel by viewModels<SignUpViewModel>()

    override fun onViewCreateFinish() {

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
                val user = User(
                    editTextEmail.text.toString().trim()
                )
                val password = editTextPassword.text.toString().trim()
                viewModel.createAccountWithEmailAndPassword(user,password)
                viewModel.saveEmail(user.toString())
                viewModel.savePassword(password)
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
         with(viewModel){
             authResult.observe(viewLifecycleOwner){
                 with(binding){
                     when(it){
                         is AuthUiState.Success->{
                             binding.btnCircularProgress.revertAnimation()
                             Toast.makeText(context, "Account created",Toast.LENGTH_LONG).show()
                             findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())

                         }
                         is AuthUiState.Error ->{
                             Toast.makeText(context,"Account can't created",Toast.LENGTH_SHORT).show()
                             binding.btnCircularProgress.revertAnimation()
                         }
                         is AuthUiState.Loading ->{
                             binding.btnCircularProgress.startAnimation()
                         }
                     }
                 }
             }
         }
    }

}