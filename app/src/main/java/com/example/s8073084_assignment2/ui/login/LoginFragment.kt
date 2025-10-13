package com.example.s8073084_assignment2.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.s8073084_assignment2.R
import com.example.s8073084_assignment2.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            // TODO: Replace with your actual class location
            viewModel.login("footscray", username, password) 
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginState.collectLatest { state ->
                binding.progressBar.isVisible = state is LoginUiState.Loading

                when (state) {
                    is LoginUiState.Success -> {
                        val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment(state.keypass)
                        findNavController().navigate(action)
                    }
                    is LoginUiState.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                    }
                    else -> Unit
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}