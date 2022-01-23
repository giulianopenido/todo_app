package com.developer.todo.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.todo.R
import com.developer.todo.databinding.FragmentHomeBinding
import com.developer.todo.ui.adapters.CategoryAdapter
import com.developer.todo.ui.adapters.TaskAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private var scaleFactor = 6
    private val binding get() = _binding!!

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mDashboard: View
    private lateinit var mTasksRecyclerView: RecyclerView
    private lateinit var mCategoriesRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        mDrawerLayout = binding.drawerLayout
        mDashboard = binding.dashboard
        mTasksRecyclerView = binding.tasksRecyclerView
        mCategoriesRecyclerView = binding.categoriesRecyclerView

        configureDrawerMenu()
        configureListeners()
        configureCategoriesList()
        configureTasksList()

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
            mDrawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private fun configureListeners() {
        binding.fabAddTask.setOnClickListener() {
            findNavController().navigate(R.id.action_nav_home_to_add_task_fragment)
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

    inner class TaskTouchHelper(dragDirs: Int, swipeDirs: Int): ItemTouchHelper.SimpleCallback(
        dragDirs, swipeDirs
    ) {
        var from: Int = 0
        var to: Int = 0
        var orderChanged: Boolean = false

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            from = viewHolder.adapterPosition
            to = target.adapterPosition
            mTasksRecyclerView.adapter!!.notifyItemMoved(from, to)
            orderChanged = true
            return orderChanged
        }


        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            homeViewModel.deleteTask(viewHolder.adapterPosition)
            mTasksRecyclerView.adapter!!.notifyItemRemoved(viewHolder.adapterPosition)
        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            super.onSelectedChanged(viewHolder, actionState)
            when(actionState) {
                 ItemTouchHelper.ACTION_STATE_IDLE -> {
                    if(orderChanged) {
                        homeViewModel.moveTask(from, to)
                        orderChanged = false
                    }
                }
            }
        }
    }


}