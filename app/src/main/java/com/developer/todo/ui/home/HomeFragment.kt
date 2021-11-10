package com.developer.todo.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.todo.R
import com.developer.todo.databinding.FragmentHomeBinding
import com.developer.todo.databinding.TaskItemBinding
import com.developer.todo.model.Task
import com.developer.todo.ui.adapters.CategoryAdapter
import com.developer.todo.ui.adapters.TaskAdapter
import java.util.*

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

    private val mTasksRecyclerView by lazy {
        binding.tasksRecyclerView
    }

    private val mCategoriesRecyclerView by lazy {
        binding.categoriesRecyclerView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        configureDrawerMenu()
        configureCategoriesList()
        configureTasksList()
        configureObservers()

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
                mDashboard.scaleY = 1 - (slideOffset / scaleFactor)
                mDashboard.scaleX = 1 - (slideOffset / scaleFactor)
            }
        }
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        binding.drawerMenuIcon.setOnClickListener() {
            mDrawerLayout.openDrawer(Gravity.LEFT)
        }
    }

    private fun configureCategoriesList() {
        mCategoriesRecyclerView.adapter = CategoryAdapter(homeViewModel.categories.value!!)
        mCategoriesRecyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)

    }

    private fun configureTasksList() {
        val adapter = TaskAdapter(homeViewModel.tasks.value!!)
        val taskTouchHelper = ItemTouchHelper(
            TaskTouchHelper(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT
            )
        )
        taskTouchHelper.attachToRecyclerView(mTasksRecyclerView)

        adapter.onCheckboxClick = {
            homeViewModel.toggleIsDone(it)
            adapter.notifyItemChanged(it)
        }

        mTasksRecyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        mTasksRecyclerView.adapter = adapter
    }

    private fun configureObservers() {
        homeViewModel.tasks.observe(viewLifecycleOwner, {
            mTasksRecyclerView.adapter?.notifyDataSetChanged()
        })


    }

    inner class TaskTouchHelper(dragDirs: Int, swipeDirs: Int): androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback(
        dragDirs, swipeDirs
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val from = viewHolder.adapterPosition
            val to = target.adapterPosition
            homeViewModel.moveTask(from, to)
//            mTasksRecyclerView.adapter!!.notifyItemMoved(from, to)
            return true
        }

        override fun onMoved(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            fromPos: Int,
            target: RecyclerView.ViewHolder,
            toPos: Int,
            x: Int,
            y: Int
        ) {
            super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            homeViewModel.deleteTask(viewHolder.adapterPosition)
            mTasksRecyclerView.adapter!!.notifyItemRemoved(viewHolder.adapterPosition)
        }

    }

}