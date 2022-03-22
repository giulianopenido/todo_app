package com.developer.todo.ui.splash

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.developer.todo.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashFragment : Fragment() {
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(viewModel.isAuthenticated()) {
            findNavController().navigate(R.id.action_splash_fragment_to_home_fragment)
        } else {
            findNavController().navigate(R.id.action_splash_fragment_to_login_fragment)
        }

    }

}