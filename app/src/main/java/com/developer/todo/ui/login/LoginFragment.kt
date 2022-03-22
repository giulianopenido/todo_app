package com.developer.todo.ui.login

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.developer.todo.R
import com.developer.todo.databinding.LoginFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        setupListeners()
        return binding.root
    }

    private fun setupListeners() {
        binding.loginButton.setOnClickListener {
            viewModel.login(
                binding.emailInput.text.toString(),
                binding.passwordInput.text.toString()
            )
        }

        viewModel.sucessfulLogin.observe(viewLifecycleOwner) {
            openHomeScreen()
        }

        viewModel.showLoading.observe(viewLifecycleOwner) {
            showLoading()
        }

        viewModel.hideLoading.observe(viewLifecycleOwner) {
            hideLoading()
        }
    }

    private fun openHomeScreen() {
        findNavController().navigate(R.id.action_login_fragment_to_home_fragment)
    }

    private fun showLoading() {
        binding.loginButton.isEnabled = false
        binding.loading.visibility = View.VISIBLE
        binding.loginButton.textScaleX = 0F

    }

    private fun hideLoading() {
        binding.loginButton.isEnabled = true
        binding.loading.visibility = View.GONE
        binding.loginButton.textScaleX = 1F
    }

}