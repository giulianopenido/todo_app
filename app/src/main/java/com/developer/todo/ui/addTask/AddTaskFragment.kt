package com.developer.todo.ui.addTask

import android.app.AlertDialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.developer.todo.R
import com.developer.todo.databinding.FragmentAddTaskBinding
import com.google.android.material.datepicker.MaterialDatePicker


class AddTaskFragment: Fragment() {

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var datePicker: MaterialDatePicker<Long>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        setupListeners()
        datePicker = setupAndGetDatePicker()
        return binding.root
    }

    fun setupListeners() {
        binding.datePicker.setOnClickListener {
            datePicker.show(requireFragmentManager(), "DATE_PICKER")
        }

        binding.closeAddTaskButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun setupAndGetDatePicker():  MaterialDatePicker<Long> {
        val datePicker = MaterialDatePicker
            .Builder
            .datePicker()
            .setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar)
            .setTitleText("Choose the due date")
            .build()

//        datePicker.addOnPositiveButtonClickListener {
//
//        }

        return datePicker
    }


}