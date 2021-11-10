package com.developer.todo.ui.adapters

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import com.developer.todo.R
import com.developer.todo.databinding.TaskItemBinding
import com.developer.todo.model.Task

class TaskAdapter(
    val tasks: MutableList<Task>
): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var onCheckboxClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
       return TaskViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
        holder.binding.doneCheckbox.setOnClickListener {
            onCheckboxClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.task =  task

            if(task.isDone) {
                binding.doneCheckbox.setImageResource(R.drawable.ic_baseline_check_circle_24)
                binding.taskTitle.paintFlags = binding.taskTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                val mixedColor = ColorUtils.blendARGB(Color.parseColor(task.category.color), Color.parseColor("#FFFFFF"), 0.75f)
                binding.doneCheckbox.setColorFilter(mixedColor)
            } else {
                binding.doneCheckbox.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
                binding.taskTitle.paintFlags = binding.taskTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                binding.doneCheckbox.setColorFilter(Color.parseColor(task.category.color))
            }
        }
    }

}

