package com.developer.todo.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.developer.todo.R
import com.developer.todo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private var scaleFactor = 6
    private val binding get() = _binding!!
    private val mDrawerLayout by lazy {
        binding.drawerLayout
    }
    private val mDashboard by lazy {
        binding.dashboard
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        configureDrawerMenu()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun configureDrawerMenu() {

        mDrawerLayout.setScrimColor(Color.TRANSPARENT)

        val actionBarDrawerToggle = object: ActionBarDrawerToggle(
            this.activity,
            mDrawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val slideX = drawerView.width * slideOffset
                mDashboard.translationX = slideX
                mDashboard.scaleY = 1 - (slideOffset / scaleFactor);
            }
        }
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        binding.drawerMenuIcon.setOnClickListener() {
            mDrawerLayout.openDrawer(Gravity.LEFT)
        }
    }

}